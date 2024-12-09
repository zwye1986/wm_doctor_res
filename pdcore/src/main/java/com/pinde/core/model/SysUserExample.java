package com.pinde.core.model;

import java.util.ArrayList;
import java.util.List;

public class SysUserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysUserExample() {
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

        public Criteria andUserPasswdIsNull() {
            addCriterion("USER_PASSWD is null");
            return (Criteria) this;
        }

        public Criteria andUserPasswdIsNotNull() {
            addCriterion("USER_PASSWD is not null");
            return (Criteria) this;
        }

        public Criteria andUserPasswdEqualTo(String value) {
            addCriterion("USER_PASSWD =", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdNotEqualTo(String value) {
            addCriterion("USER_PASSWD <>", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdGreaterThan(String value) {
            addCriterion("USER_PASSWD >", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PASSWD >=", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdLessThan(String value) {
            addCriterion("USER_PASSWD <", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdLessThanOrEqualTo(String value) {
            addCriterion("USER_PASSWD <=", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdLike(String value) {
            addCriterion("USER_PASSWD like", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdNotLike(String value) {
            addCriterion("USER_PASSWD not like", value, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdIn(List<String> values) {
            addCriterion("USER_PASSWD in", values, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdNotIn(List<String> values) {
            addCriterion("USER_PASSWD not in", values, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdBetween(String value1, String value2) {
            addCriterion("USER_PASSWD between", value1, value2, "userPasswd");
            return (Criteria) this;
        }

        public Criteria andUserPasswdNotBetween(String value1, String value2) {
            addCriterion("USER_PASSWD not between", value1, value2, "userPasswd");
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

        public Criteria andSexIdIsNull() {
            addCriterion("SEX_ID is null");
            return (Criteria) this;
        }

        public Criteria andSexIdIsNotNull() {
            addCriterion("SEX_ID is not null");
            return (Criteria) this;
        }

        public Criteria andSexIdEqualTo(String value) {
            addCriterion("SEX_ID =", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotEqualTo(String value) {
            addCriterion("SEX_ID <>", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThan(String value) {
            addCriterion("SEX_ID >", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_ID >=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThan(String value) {
            addCriterion("SEX_ID <", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLessThanOrEqualTo(String value) {
            addCriterion("SEX_ID <=", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdLike(String value) {
            addCriterion("SEX_ID like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotLike(String value) {
            addCriterion("SEX_ID not like", value, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdIn(List<String> values) {
            addCriterion("SEX_ID in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotIn(List<String> values) {
            addCriterion("SEX_ID not in", values, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdBetween(String value1, String value2) {
            addCriterion("SEX_ID between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexIdNotBetween(String value1, String value2) {
            addCriterion("SEX_ID not between", value1, value2, "sexId");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("SEX_NAME is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("SEX_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("SEX_NAME =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("SEX_NAME <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("SEX_NAME >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("SEX_NAME >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("SEX_NAME <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("SEX_NAME <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("SEX_NAME like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("SEX_NAME not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("SEX_NAME in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("SEX_NAME not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("SEX_NAME between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("SEX_NAME not between", value1, value2, "sexName");
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

        public Criteria andUserPhoneIsNull() {
            addCriterion("USER_PHONE is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("USER_PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("USER_PHONE =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("USER_PHONE <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("USER_PHONE >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("USER_PHONE <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("USER_PHONE like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("USER_PHONE not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("USER_PHONE in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("USER_PHONE not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("USER_PHONE between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNull() {
            addCriterion("USER_EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailIsNotNull() {
            addCriterion("USER_EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailEqualTo(String value) {
            addCriterion("USER_EMAIL =", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotEqualTo(String value) {
            addCriterion("USER_EMAIL <>", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThan(String value) {
            addCriterion("USER_EMAIL >", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailGreaterThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL >=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThan(String value) {
            addCriterion("USER_EMAIL <", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLessThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL <=", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailLike(String value) {
            addCriterion("USER_EMAIL like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotLike(String value) {
            addCriterion("USER_EMAIL not like", value, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailIn(List<String> values) {
            addCriterion("USER_EMAIL in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotIn(List<String> values) {
            addCriterion("USER_EMAIL not in", values, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailBetween(String value1, String value2) {
            addCriterion("USER_EMAIL between", value1, value2, "userEmail");
            return (Criteria) this;
        }

        public Criteria andUserEmailNotBetween(String value1, String value2) {
            addCriterion("USER_EMAIL not between", value1, value2, "userEmail");
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

        public Criteria andOrgFlowIsNull() {
            addCriterion("ORG_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIsNotNull() {
            addCriterion("ORG_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowEqualTo(String value) {
            addCriterion("ORG_FLOW =", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotEqualTo(String value) {
            addCriterion("ORG_FLOW <>", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThan(String value) {
            addCriterion("ORG_FLOW >", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW >=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThan(String value) {
            addCriterion("ORG_FLOW <", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW <=", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowLike(String value) {
            addCriterion("ORG_FLOW like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotLike(String value) {
            addCriterion("ORG_FLOW not like", value, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowIn(List<String> values) {
            addCriterion("ORG_FLOW in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotIn(List<String> values) {
            addCriterion("ORG_FLOW not in", values, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowBetween(String value1, String value2) {
            addCriterion("ORG_FLOW between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgFlowNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW not between", value1, value2, "orgFlow");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNull() {
            addCriterion("ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andOrgNameIsNotNull() {
            addCriterion("ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andOrgNameEqualTo(String value) {
            addCriterion("ORG_NAME =", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotEqualTo(String value) {
            addCriterion("ORG_NAME <>", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThan(String value) {
            addCriterion("ORG_NAME >", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_NAME >=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThan(String value) {
            addCriterion("ORG_NAME <", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLessThanOrEqualTo(String value) {
            addCriterion("ORG_NAME <=", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameLike(String value) {
            addCriterion("ORG_NAME like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotLike(String value) {
            addCriterion("ORG_NAME not like", value, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameIn(List<String> values) {
            addCriterion("ORG_NAME in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotIn(List<String> values) {
            addCriterion("ORG_NAME not in", values, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameBetween(String value1, String value2) {
            addCriterion("ORG_NAME between", value1, value2, "orgName");
            return (Criteria) this;
        }

        public Criteria andOrgNameNotBetween(String value1, String value2) {
            addCriterion("ORG_NAME not between", value1, value2, "orgName");
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

        public Criteria andUserBirthdayIsNull() {
            addCriterion("USER_BIRTHDAY is null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIsNotNull() {
            addCriterion("USER_BIRTHDAY is not null");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayEqualTo(String value) {
            addCriterion("USER_BIRTHDAY =", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotEqualTo(String value) {
            addCriterion("USER_BIRTHDAY <>", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThan(String value) {
            addCriterion("USER_BIRTHDAY >", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayGreaterThanOrEqualTo(String value) {
            addCriterion("USER_BIRTHDAY >=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThan(String value) {
            addCriterion("USER_BIRTHDAY <", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLessThanOrEqualTo(String value) {
            addCriterion("USER_BIRTHDAY <=", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayLike(String value) {
            addCriterion("USER_BIRTHDAY like", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotLike(String value) {
            addCriterion("USER_BIRTHDAY not like", value, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayIn(List<String> values) {
            addCriterion("USER_BIRTHDAY in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotIn(List<String> values) {
            addCriterion("USER_BIRTHDAY not in", values, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayBetween(String value1, String value2) {
            addCriterion("USER_BIRTHDAY between", value1, value2, "userBirthday");
            return (Criteria) this;
        }

        public Criteria andUserBirthdayNotBetween(String value1, String value2) {
            addCriterion("USER_BIRTHDAY not between", value1, value2, "userBirthday");
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

        public Criteria andDegreeIdIsNull() {
            addCriterion("DEGREE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDegreeIdIsNotNull() {
            addCriterion("DEGREE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeIdEqualTo(String value) {
            addCriterion("DEGREE_ID =", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotEqualTo(String value) {
            addCriterion("DEGREE_ID <>", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdGreaterThan(String value) {
            addCriterion("DEGREE_ID >", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_ID >=", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdLessThan(String value) {
            addCriterion("DEGREE_ID <", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_ID <=", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdLike(String value) {
            addCriterion("DEGREE_ID like", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotLike(String value) {
            addCriterion("DEGREE_ID not like", value, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdIn(List<String> values) {
            addCriterion("DEGREE_ID in", values, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotIn(List<String> values) {
            addCriterion("DEGREE_ID not in", values, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdBetween(String value1, String value2) {
            addCriterion("DEGREE_ID between", value1, value2, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeIdNotBetween(String value1, String value2) {
            addCriterion("DEGREE_ID not between", value1, value2, "degreeId");
            return (Criteria) this;
        }

        public Criteria andDegreeNameIsNull() {
            addCriterion("DEGREE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDegreeNameIsNotNull() {
            addCriterion("DEGREE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDegreeNameEqualTo(String value) {
            addCriterion("DEGREE_NAME =", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotEqualTo(String value) {
            addCriterion("DEGREE_NAME <>", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameGreaterThan(String value) {
            addCriterion("DEGREE_NAME >", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameGreaterThanOrEqualTo(String value) {
            addCriterion("DEGREE_NAME >=", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameLessThan(String value) {
            addCriterion("DEGREE_NAME <", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameLessThanOrEqualTo(String value) {
            addCriterion("DEGREE_NAME <=", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameLike(String value) {
            addCriterion("DEGREE_NAME like", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotLike(String value) {
            addCriterion("DEGREE_NAME not like", value, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameIn(List<String> values) {
            addCriterion("DEGREE_NAME in", values, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotIn(List<String> values) {
            addCriterion("DEGREE_NAME not in", values, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameBetween(String value1, String value2) {
            addCriterion("DEGREE_NAME between", value1, value2, "degreeName");
            return (Criteria) this;
        }

        public Criteria andDegreeNameNotBetween(String value1, String value2) {
            addCriterion("DEGREE_NAME not between", value1, value2, "degreeName");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNull() {
            addCriterion("EDUCATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andEducationIdIsNotNull() {
            addCriterion("EDUCATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andEducationIdEqualTo(String value) {
            addCriterion("EDUCATION_ID =", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotEqualTo(String value) {
            addCriterion("EDUCATION_ID <>", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThan(String value) {
            addCriterion("EDUCATION_ID >", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_ID >=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThan(String value) {
            addCriterion("EDUCATION_ID <", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_ID <=", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdLike(String value) {
            addCriterion("EDUCATION_ID like", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotLike(String value) {
            addCriterion("EDUCATION_ID not like", value, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdIn(List<String> values) {
            addCriterion("EDUCATION_ID in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotIn(List<String> values) {
            addCriterion("EDUCATION_ID not in", values, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdBetween(String value1, String value2) {
            addCriterion("EDUCATION_ID between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationIdNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_ID not between", value1, value2, "educationId");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNull() {
            addCriterion("EDUCATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNotNull() {
            addCriterion("EDUCATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andEducationNameEqualTo(String value) {
            addCriterion("EDUCATION_NAME =", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotEqualTo(String value) {
            addCriterion("EDUCATION_NAME <>", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThan(String value) {
            addCriterion("EDUCATION_NAME >", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThanOrEqualTo(String value) {
            addCriterion("EDUCATION_NAME >=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThan(String value) {
            addCriterion("EDUCATION_NAME <", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThanOrEqualTo(String value) {
            addCriterion("EDUCATION_NAME <=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLike(String value) {
            addCriterion("EDUCATION_NAME like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotLike(String value) {
            addCriterion("EDUCATION_NAME not like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameIn(List<String> values) {
            addCriterion("EDUCATION_NAME in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotIn(List<String> values) {
            addCriterion("EDUCATION_NAME not in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameBetween(String value1, String value2) {
            addCriterion("EDUCATION_NAME between", value1, value2, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotBetween(String value1, String value2) {
            addCriterion("EDUCATION_NAME not between", value1, value2, "educationName");
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

        public Criteria andSrmExpertFlagIsNull() {
            addCriterion("SRM_EXPERT_FLAG is null");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagIsNotNull() {
            addCriterion("SRM_EXPERT_FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagEqualTo(String value) {
            addCriterion("SRM_EXPERT_FLAG =", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagNotEqualTo(String value) {
            addCriterion("SRM_EXPERT_FLAG <>", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagGreaterThan(String value) {
            addCriterion("SRM_EXPERT_FLAG >", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagGreaterThanOrEqualTo(String value) {
            addCriterion("SRM_EXPERT_FLAG >=", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagLessThan(String value) {
            addCriterion("SRM_EXPERT_FLAG <", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagLessThanOrEqualTo(String value) {
            addCriterion("SRM_EXPERT_FLAG <=", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagLike(String value) {
            addCriterion("SRM_EXPERT_FLAG like", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagNotLike(String value) {
            addCriterion("SRM_EXPERT_FLAG not like", value, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagIn(List<String> values) {
            addCriterion("SRM_EXPERT_FLAG in", values, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagNotIn(List<String> values) {
            addCriterion("SRM_EXPERT_FLAG not in", values, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagBetween(String value1, String value2) {
            addCriterion("SRM_EXPERT_FLAG between", value1, value2, "srmExpertFlag");
            return (Criteria) this;
        }

        public Criteria andSrmExpertFlagNotBetween(String value1, String value2) {
            addCriterion("SRM_EXPERT_FLAG not between", value1, value2, "srmExpertFlag");
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

        public Criteria andWeiXinIdIsNull() {
            addCriterion("WEI_XIN_ID is null");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdIsNotNull() {
            addCriterion("WEI_XIN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdEqualTo(String value) {
            addCriterion("WEI_XIN_ID =", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdNotEqualTo(String value) {
            addCriterion("WEI_XIN_ID <>", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdGreaterThan(String value) {
            addCriterion("WEI_XIN_ID >", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdGreaterThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_ID >=", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdLessThan(String value) {
            addCriterion("WEI_XIN_ID <", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdLessThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_ID <=", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdLike(String value) {
            addCriterion("WEI_XIN_ID like", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdNotLike(String value) {
            addCriterion("WEI_XIN_ID not like", value, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdIn(List<String> values) {
            addCriterion("WEI_XIN_ID in", values, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdNotIn(List<String> values) {
            addCriterion("WEI_XIN_ID not in", values, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdBetween(String value1, String value2) {
            addCriterion("WEI_XIN_ID between", value1, value2, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinIdNotBetween(String value1, String value2) {
            addCriterion("WEI_XIN_ID not between", value1, value2, "weiXinId");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameIsNull() {
            addCriterion("WEI_XIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameIsNotNull() {
            addCriterion("WEI_XIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameEqualTo(String value) {
            addCriterion("WEI_XIN_NAME =", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotEqualTo(String value) {
            addCriterion("WEI_XIN_NAME <>", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameGreaterThan(String value) {
            addCriterion("WEI_XIN_NAME >", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameGreaterThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_NAME >=", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameLessThan(String value) {
            addCriterion("WEI_XIN_NAME <", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameLessThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_NAME <=", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameLike(String value) {
            addCriterion("WEI_XIN_NAME like", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotLike(String value) {
            addCriterion("WEI_XIN_NAME not like", value, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameIn(List<String> values) {
            addCriterion("WEI_XIN_NAME in", values, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotIn(List<String> values) {
            addCriterion("WEI_XIN_NAME not in", values, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameBetween(String value1, String value2) {
            addCriterion("WEI_XIN_NAME between", value1, value2, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinNameNotBetween(String value1, String value2) {
            addCriterion("WEI_XIN_NAME not between", value1, value2, "weiXinName");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdIsNull() {
            addCriterion("WEI_XIN_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdIsNotNull() {
            addCriterion("WEI_XIN_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_ID =", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdNotEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_ID <>", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdGreaterThan(String value) {
            addCriterion("WEI_XIN_STATUS_ID >", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_ID >=", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdLessThan(String value) {
            addCriterion("WEI_XIN_STATUS_ID <", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdLessThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_ID <=", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdLike(String value) {
            addCriterion("WEI_XIN_STATUS_ID like", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdNotLike(String value) {
            addCriterion("WEI_XIN_STATUS_ID not like", value, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdIn(List<String> values) {
            addCriterion("WEI_XIN_STATUS_ID in", values, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdNotIn(List<String> values) {
            addCriterion("WEI_XIN_STATUS_ID not in", values, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdBetween(String value1, String value2) {
            addCriterion("WEI_XIN_STATUS_ID between", value1, value2, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusIdNotBetween(String value1, String value2) {
            addCriterion("WEI_XIN_STATUS_ID not between", value1, value2, "weiXinStatusId");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescIsNull() {
            addCriterion("WEI_XIN_STATUS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescIsNotNull() {
            addCriterion("WEI_XIN_STATUS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_DESC =", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescNotEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_DESC <>", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescGreaterThan(String value) {
            addCriterion("WEI_XIN_STATUS_DESC >", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_DESC >=", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescLessThan(String value) {
            addCriterion("WEI_XIN_STATUS_DESC <", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescLessThanOrEqualTo(String value) {
            addCriterion("WEI_XIN_STATUS_DESC <=", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescLike(String value) {
            addCriterion("WEI_XIN_STATUS_DESC like", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescNotLike(String value) {
            addCriterion("WEI_XIN_STATUS_DESC not like", value, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescIn(List<String> values) {
            addCriterion("WEI_XIN_STATUS_DESC in", values, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescNotIn(List<String> values) {
            addCriterion("WEI_XIN_STATUS_DESC not in", values, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescBetween(String value1, String value2) {
            addCriterion("WEI_XIN_STATUS_DESC between", value1, value2, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andWeiXinStatusDescNotBetween(String value1, String value2) {
            addCriterion("WEI_XIN_STATUS_DESC not between", value1, value2, "weiXinStatusDesc");
            return (Criteria) this;
        }

        public Criteria andNationIdIsNull() {
            addCriterion("NATION_ID is null");
            return (Criteria) this;
        }

        public Criteria andNationIdIsNotNull() {
            addCriterion("NATION_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNationIdEqualTo(String value) {
            addCriterion("NATION_ID =", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotEqualTo(String value) {
            addCriterion("NATION_ID <>", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdGreaterThan(String value) {
            addCriterion("NATION_ID >", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdGreaterThanOrEqualTo(String value) {
            addCriterion("NATION_ID >=", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdLessThan(String value) {
            addCriterion("NATION_ID <", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdLessThanOrEqualTo(String value) {
            addCriterion("NATION_ID <=", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdLike(String value) {
            addCriterion("NATION_ID like", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotLike(String value) {
            addCriterion("NATION_ID not like", value, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdIn(List<String> values) {
            addCriterion("NATION_ID in", values, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotIn(List<String> values) {
            addCriterion("NATION_ID not in", values, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdBetween(String value1, String value2) {
            addCriterion("NATION_ID between", value1, value2, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationIdNotBetween(String value1, String value2) {
            addCriterion("NATION_ID not between", value1, value2, "nationId");
            return (Criteria) this;
        }

        public Criteria andNationNameIsNull() {
            addCriterion("NATION_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNationNameIsNotNull() {
            addCriterion("NATION_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNationNameEqualTo(String value) {
            addCriterion("NATION_NAME =", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotEqualTo(String value) {
            addCriterion("NATION_NAME <>", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameGreaterThan(String value) {
            addCriterion("NATION_NAME >", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATION_NAME >=", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameLessThan(String value) {
            addCriterion("NATION_NAME <", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameLessThanOrEqualTo(String value) {
            addCriterion("NATION_NAME <=", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameLike(String value) {
            addCriterion("NATION_NAME like", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotLike(String value) {
            addCriterion("NATION_NAME not like", value, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameIn(List<String> values) {
            addCriterion("NATION_NAME in", values, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotIn(List<String> values) {
            addCriterion("NATION_NAME not in", values, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameBetween(String value1, String value2) {
            addCriterion("NATION_NAME between", value1, value2, "nationName");
            return (Criteria) this;
        }

        public Criteria andNationNameNotBetween(String value1, String value2) {
            addCriterion("NATION_NAME not between", value1, value2, "nationName");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNull() {
            addCriterion("USER_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNotNull() {
            addCriterion("USER_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddressEqualTo(String value) {
            addCriterion("USER_ADDRESS =", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotEqualTo(String value) {
            addCriterion("USER_ADDRESS <>", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThan(String value) {
            addCriterion("USER_ADDRESS >", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ADDRESS >=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThan(String value) {
            addCriterion("USER_ADDRESS <", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThanOrEqualTo(String value) {
            addCriterion("USER_ADDRESS <=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLike(String value) {
            addCriterion("USER_ADDRESS like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotLike(String value) {
            addCriterion("USER_ADDRESS not like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressIn(List<String> values) {
            addCriterion("USER_ADDRESS in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotIn(List<String> values) {
            addCriterion("USER_ADDRESS not in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressBetween(String value1, String value2) {
            addCriterion("USER_ADDRESS between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotBetween(String value1, String value2) {
            addCriterion("USER_ADDRESS not between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdIsNull() {
            addCriterion("USER_EMAIL_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdIsNotNull() {
            addCriterion("USER_EMAIL_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_ID =", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdNotEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_ID <>", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdGreaterThan(String value) {
            addCriterion("USER_EMAIL_STATUS_ID >", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_ID >=", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdLessThan(String value) {
            addCriterion("USER_EMAIL_STATUS_ID <", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdLessThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_ID <=", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdLike(String value) {
            addCriterion("USER_EMAIL_STATUS_ID like", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdNotLike(String value) {
            addCriterion("USER_EMAIL_STATUS_ID not like", value, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdIn(List<String> values) {
            addCriterion("USER_EMAIL_STATUS_ID in", values, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdNotIn(List<String> values) {
            addCriterion("USER_EMAIL_STATUS_ID not in", values, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdBetween(String value1, String value2) {
            addCriterion("USER_EMAIL_STATUS_ID between", value1, value2, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusIdNotBetween(String value1, String value2) {
            addCriterion("USER_EMAIL_STATUS_ID not between", value1, value2, "userEmailStatusId");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescIsNull() {
            addCriterion("USER_EMAIL_STATUS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescIsNotNull() {
            addCriterion("USER_EMAIL_STATUS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC =", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescNotEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC <>", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescGreaterThan(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC >", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC >=", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescLessThan(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC <", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescLessThanOrEqualTo(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC <=", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescLike(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC like", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescNotLike(String value) {
            addCriterion("USER_EMAIL_STATUS_DESC not like", value, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescIn(List<String> values) {
            addCriterion("USER_EMAIL_STATUS_DESC in", values, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescNotIn(List<String> values) {
            addCriterion("USER_EMAIL_STATUS_DESC not in", values, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescBetween(String value1, String value2) {
            addCriterion("USER_EMAIL_STATUS_DESC between", value1, value2, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserEmailStatusDescNotBetween(String value1, String value2) {
            addCriterion("USER_EMAIL_STATUS_DESC not between", value1, value2, "userEmailStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdIsNull() {
            addCriterion("USER_PHONE_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdIsNotNull() {
            addCriterion("USER_PHONE_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_ID =", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdNotEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_ID <>", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdGreaterThan(String value) {
            addCriterion("USER_PHONE_STATUS_ID >", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_ID >=", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdLessThan(String value) {
            addCriterion("USER_PHONE_STATUS_ID <", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_ID <=", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdLike(String value) {
            addCriterion("USER_PHONE_STATUS_ID like", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdNotLike(String value) {
            addCriterion("USER_PHONE_STATUS_ID not like", value, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdIn(List<String> values) {
            addCriterion("USER_PHONE_STATUS_ID in", values, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdNotIn(List<String> values) {
            addCriterion("USER_PHONE_STATUS_ID not in", values, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdBetween(String value1, String value2) {
            addCriterion("USER_PHONE_STATUS_ID between", value1, value2, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusIdNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE_STATUS_ID not between", value1, value2, "userPhoneStatusId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescIsNull() {
            addCriterion("USER_PHONE_STATUS_DESC is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescIsNotNull() {
            addCriterion("USER_PHONE_STATUS_DESC is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_DESC =", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescNotEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_DESC <>", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescGreaterThan(String value) {
            addCriterion("USER_PHONE_STATUS_DESC >", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_DESC >=", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescLessThan(String value) {
            addCriterion("USER_PHONE_STATUS_DESC <", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescLessThanOrEqualTo(String value) {
            addCriterion("USER_PHONE_STATUS_DESC <=", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescLike(String value) {
            addCriterion("USER_PHONE_STATUS_DESC like", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescNotLike(String value) {
            addCriterion("USER_PHONE_STATUS_DESC not like", value, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescIn(List<String> values) {
            addCriterion("USER_PHONE_STATUS_DESC in", values, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescNotIn(List<String> values) {
            addCriterion("USER_PHONE_STATUS_DESC not in", values, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescBetween(String value1, String value2) {
            addCriterion("USER_PHONE_STATUS_DESC between", value1, value2, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andUserPhoneStatusDescNotBetween(String value1, String value2) {
            addCriterion("USER_PHONE_STATUS_DESC not between", value1, value2, "userPhoneStatusDesc");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdIsNull() {
            addCriterion("CRET_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdIsNotNull() {
            addCriterion("CRET_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdEqualTo(String value) {
            addCriterion("CRET_TYPE_ID =", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotEqualTo(String value) {
            addCriterion("CRET_TYPE_ID <>", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdGreaterThan(String value) {
            addCriterion("CRET_TYPE_ID >", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_ID >=", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdLessThan(String value) {
            addCriterion("CRET_TYPE_ID <", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdLessThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_ID <=", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdLike(String value) {
            addCriterion("CRET_TYPE_ID like", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotLike(String value) {
            addCriterion("CRET_TYPE_ID not like", value, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdIn(List<String> values) {
            addCriterion("CRET_TYPE_ID in", values, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotIn(List<String> values) {
            addCriterion("CRET_TYPE_ID not in", values, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_ID between", value1, value2, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeIdNotBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_ID not between", value1, value2, "cretTypeId");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameIsNull() {
            addCriterion("CRET_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameIsNotNull() {
            addCriterion("CRET_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME =", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME <>", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameGreaterThan(String value) {
            addCriterion("CRET_TYPE_NAME >", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME >=", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameLessThan(String value) {
            addCriterion("CRET_TYPE_NAME <", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameLessThanOrEqualTo(String value) {
            addCriterion("CRET_TYPE_NAME <=", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameLike(String value) {
            addCriterion("CRET_TYPE_NAME like", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotLike(String value) {
            addCriterion("CRET_TYPE_NAME not like", value, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameIn(List<String> values) {
            addCriterion("CRET_TYPE_NAME in", values, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotIn(List<String> values) {
            addCriterion("CRET_TYPE_NAME not in", values, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_NAME between", value1, value2, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andCretTypeNameNotBetween(String value1, String value2) {
            addCriterion("CRET_TYPE_NAME not between", value1, value2, "cretTypeName");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgIsNull() {
            addCriterion("USER_HEAD_IMG is null");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgIsNotNull() {
            addCriterion("USER_HEAD_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgEqualTo(String value) {
            addCriterion("USER_HEAD_IMG =", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgNotEqualTo(String value) {
            addCriterion("USER_HEAD_IMG <>", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgGreaterThan(String value) {
            addCriterion("USER_HEAD_IMG >", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgGreaterThanOrEqualTo(String value) {
            addCriterion("USER_HEAD_IMG >=", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgLessThan(String value) {
            addCriterion("USER_HEAD_IMG <", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgLessThanOrEqualTo(String value) {
            addCriterion("USER_HEAD_IMG <=", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgLike(String value) {
            addCriterion("USER_HEAD_IMG like", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgNotLike(String value) {
            addCriterion("USER_HEAD_IMG not like", value, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgIn(List<String> values) {
            addCriterion("USER_HEAD_IMG in", values, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgNotIn(List<String> values) {
            addCriterion("USER_HEAD_IMG not in", values, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgBetween(String value1, String value2) {
            addCriterion("USER_HEAD_IMG between", value1, value2, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andUserHeadImgNotBetween(String value1, String value2) {
            addCriterion("USER_HEAD_IMG not between", value1, value2, "userHeadImg");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdIsNull() {
            addCriterion("POLITICS_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdIsNotNull() {
            addCriterion("POLITICS_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID =", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID <>", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdGreaterThan(String value) {
            addCriterion("POLITICS_STATUS_ID >", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID >=", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdLessThan(String value) {
            addCriterion("POLITICS_STATUS_ID <", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdLessThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_ID <=", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdLike(String value) {
            addCriterion("POLITICS_STATUS_ID like", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotLike(String value) {
            addCriterion("POLITICS_STATUS_ID not like", value, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdIn(List<String> values) {
            addCriterion("POLITICS_STATUS_ID in", values, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotIn(List<String> values) {
            addCriterion("POLITICS_STATUS_ID not in", values, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_ID between", value1, value2, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusIdNotBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_ID not between", value1, value2, "politicsStatusId");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameIsNull() {
            addCriterion("POLITICS_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameIsNotNull() {
            addCriterion("POLITICS_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME =", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME <>", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameGreaterThan(String value) {
            addCriterion("POLITICS_STATUS_NAME >", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME >=", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameLessThan(String value) {
            addCriterion("POLITICS_STATUS_NAME <", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameLessThanOrEqualTo(String value) {
            addCriterion("POLITICS_STATUS_NAME <=", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameLike(String value) {
            addCriterion("POLITICS_STATUS_NAME like", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotLike(String value) {
            addCriterion("POLITICS_STATUS_NAME not like", value, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameIn(List<String> values) {
            addCriterion("POLITICS_STATUS_NAME in", values, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotIn(List<String> values) {
            addCriterion("POLITICS_STATUS_NAME not in", values, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_NAME between", value1, value2, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andPoliticsStatusNameNotBetween(String value1, String value2) {
            addCriterion("POLITICS_STATUS_NAME not between", value1, value2, "politicsStatusName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdIsNull() {
            addCriterion("NATIVE_PLACE_PROV_ID is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdIsNotNull() {
            addCriterion("NATIVE_PLACE_PROV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID =", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdNotEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID <>", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdGreaterThan(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID >", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdGreaterThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID >=", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdLessThan(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID <", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdLessThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID <=", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdLike(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID like", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdNotLike(String value) {
            addCriterion("NATIVE_PLACE_PROV_ID not like", value, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdIn(List<String> values) {
            addCriterion("NATIVE_PLACE_PROV_ID in", values, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdNotIn(List<String> values) {
            addCriterion("NATIVE_PLACE_PROV_ID not in", values, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_PROV_ID between", value1, value2, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvIdNotBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_PROV_ID not between", value1, value2, "nativePlaceProvId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdIsNull() {
            addCriterion("NATIVE_PLACE_CITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdIsNotNull() {
            addCriterion("NATIVE_PLACE_CITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID =", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdNotEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID <>", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdGreaterThan(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID >", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdGreaterThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID >=", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdLessThan(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID <", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdLessThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID <=", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdLike(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID like", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdNotLike(String value) {
            addCriterion("NATIVE_PLACE_CITY_ID not like", value, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdIn(List<String> values) {
            addCriterion("NATIVE_PLACE_CITY_ID in", values, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdNotIn(List<String> values) {
            addCriterion("NATIVE_PLACE_CITY_ID not in", values, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_CITY_ID between", value1, value2, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityIdNotBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_CITY_ID not between", value1, value2, "nativePlaceCityId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdIsNull() {
            addCriterion("DOMICILE_PLACE_ID is null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdIsNotNull() {
            addCriterion("DOMICILE_PLACE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID =", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID <>", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdGreaterThan(String value) {
            addCriterion("DOMICILE_PLACE_ID >", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdGreaterThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID >=", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdLessThan(String value) {
            addCriterion("DOMICILE_PLACE_ID <", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdLessThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ID <=", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdLike(String value) {
            addCriterion("DOMICILE_PLACE_ID like", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotLike(String value) {
            addCriterion("DOMICILE_PLACE_ID not like", value, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_ID in", values, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_ID not in", values, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_ID between", value1, value2, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceIdNotBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_ID not between", value1, value2, "domicilePlaceId");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameIsNull() {
            addCriterion("DOMICILE_PLACE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameIsNotNull() {
            addCriterion("DOMICILE_PLACE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME =", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME <>", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameGreaterThan(String value) {
            addCriterion("DOMICILE_PLACE_NAME >", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameGreaterThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME >=", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameLessThan(String value) {
            addCriterion("DOMICILE_PLACE_NAME <", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameLessThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_NAME <=", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameLike(String value) {
            addCriterion("DOMICILE_PLACE_NAME like", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotLike(String value) {
            addCriterion("DOMICILE_PLACE_NAME not like", value, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_NAME in", values, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_NAME not in", values, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_NAME between", value1, value2, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceNameNotBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_NAME not between", value1, value2, "domicilePlaceName");
            return (Criteria) this;
        }

        public Criteria andUserQqIsNull() {
            addCriterion("USER_QQ is null");
            return (Criteria) this;
        }

        public Criteria andUserQqIsNotNull() {
            addCriterion("USER_QQ is not null");
            return (Criteria) this;
        }

        public Criteria andUserQqEqualTo(String value) {
            addCriterion("USER_QQ =", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotEqualTo(String value) {
            addCriterion("USER_QQ <>", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqGreaterThan(String value) {
            addCriterion("USER_QQ >", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqGreaterThanOrEqualTo(String value) {
            addCriterion("USER_QQ >=", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLessThan(String value) {
            addCriterion("USER_QQ <", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLessThanOrEqualTo(String value) {
            addCriterion("USER_QQ <=", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqLike(String value) {
            addCriterion("USER_QQ like", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotLike(String value) {
            addCriterion("USER_QQ not like", value, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqIn(List<String> values) {
            addCriterion("USER_QQ in", values, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotIn(List<String> values) {
            addCriterion("USER_QQ not in", values, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqBetween(String value1, String value2) {
            addCriterion("USER_QQ between", value1, value2, "userQq");
            return (Criteria) this;
        }

        public Criteria andUserQqNotBetween(String value1, String value2) {
            addCriterion("USER_QQ not between", value1, value2, "userQq");
            return (Criteria) this;
        }

        public Criteria andMaritalIdIsNull() {
            addCriterion("MARITAL_ID is null");
            return (Criteria) this;
        }

        public Criteria andMaritalIdIsNotNull() {
            addCriterion("MARITAL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalIdEqualTo(String value) {
            addCriterion("MARITAL_ID =", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotEqualTo(String value) {
            addCriterion("MARITAL_ID <>", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdGreaterThan(String value) {
            addCriterion("MARITAL_ID >", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdGreaterThanOrEqualTo(String value) {
            addCriterion("MARITAL_ID >=", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdLessThan(String value) {
            addCriterion("MARITAL_ID <", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdLessThanOrEqualTo(String value) {
            addCriterion("MARITAL_ID <=", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdLike(String value) {
            addCriterion("MARITAL_ID like", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotLike(String value) {
            addCriterion("MARITAL_ID not like", value, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdIn(List<String> values) {
            addCriterion("MARITAL_ID in", values, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotIn(List<String> values) {
            addCriterion("MARITAL_ID not in", values, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdBetween(String value1, String value2) {
            addCriterion("MARITAL_ID between", value1, value2, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalIdNotBetween(String value1, String value2) {
            addCriterion("MARITAL_ID not between", value1, value2, "maritalId");
            return (Criteria) this;
        }

        public Criteria andMaritalNameIsNull() {
            addCriterion("MARITAL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMaritalNameIsNotNull() {
            addCriterion("MARITAL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMaritalNameEqualTo(String value) {
            addCriterion("MARITAL_NAME =", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotEqualTo(String value) {
            addCriterion("MARITAL_NAME <>", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameGreaterThan(String value) {
            addCriterion("MARITAL_NAME >", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameGreaterThanOrEqualTo(String value) {
            addCriterion("MARITAL_NAME >=", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameLessThan(String value) {
            addCriterion("MARITAL_NAME <", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameLessThanOrEqualTo(String value) {
            addCriterion("MARITAL_NAME <=", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameLike(String value) {
            addCriterion("MARITAL_NAME like", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotLike(String value) {
            addCriterion("MARITAL_NAME not like", value, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameIn(List<String> values) {
            addCriterion("MARITAL_NAME in", values, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotIn(List<String> values) {
            addCriterion("MARITAL_NAME not in", values, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameBetween(String value1, String value2) {
            addCriterion("MARITAL_NAME between", value1, value2, "maritalName");
            return (Criteria) this;
        }

        public Criteria andMaritalNameNotBetween(String value1, String value2) {
            addCriterion("MARITAL_NAME not between", value1, value2, "maritalName");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressIsNull() {
            addCriterion("DOMICILE_PLACE_ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressIsNotNull() {
            addCriterion("DOMICILE_PLACE_ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS =", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressNotEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS <>", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressGreaterThan(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS >", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressGreaterThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS >=", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressLessThan(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS <", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressLessThanOrEqualTo(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS <=", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressLike(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS like", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressNotLike(String value) {
            addCriterion("DOMICILE_PLACE_ADDRESS not like", value, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_ADDRESS in", values, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressNotIn(List<String> values) {
            addCriterion("DOMICILE_PLACE_ADDRESS not in", values, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_ADDRESS between", value1, value2, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andDomicilePlaceAddressNotBetween(String value1, String value2) {
            addCriterion("DOMICILE_PLACE_ADDRESS not between", value1, value2, "domicilePlaceAddress");
            return (Criteria) this;
        }

        public Criteria andBearIdIsNull() {
            addCriterion("BEAR_ID is null");
            return (Criteria) this;
        }

        public Criteria andBearIdIsNotNull() {
            addCriterion("BEAR_ID is not null");
            return (Criteria) this;
        }

        public Criteria andBearIdEqualTo(String value) {
            addCriterion("BEAR_ID =", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdNotEqualTo(String value) {
            addCriterion("BEAR_ID <>", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdGreaterThan(String value) {
            addCriterion("BEAR_ID >", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdGreaterThanOrEqualTo(String value) {
            addCriterion("BEAR_ID >=", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdLessThan(String value) {
            addCriterion("BEAR_ID <", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdLessThanOrEqualTo(String value) {
            addCriterion("BEAR_ID <=", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdLike(String value) {
            addCriterion("BEAR_ID like", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdNotLike(String value) {
            addCriterion("BEAR_ID not like", value, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdIn(List<String> values) {
            addCriterion("BEAR_ID in", values, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdNotIn(List<String> values) {
            addCriterion("BEAR_ID not in", values, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdBetween(String value1, String value2) {
            addCriterion("BEAR_ID between", value1, value2, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearIdNotBetween(String value1, String value2) {
            addCriterion("BEAR_ID not between", value1, value2, "bearId");
            return (Criteria) this;
        }

        public Criteria andBearNameIsNull() {
            addCriterion("BEAR_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBearNameIsNotNull() {
            addCriterion("BEAR_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBearNameEqualTo(String value) {
            addCriterion("BEAR_NAME =", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameNotEqualTo(String value) {
            addCriterion("BEAR_NAME <>", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameGreaterThan(String value) {
            addCriterion("BEAR_NAME >", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameGreaterThanOrEqualTo(String value) {
            addCriterion("BEAR_NAME >=", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameLessThan(String value) {
            addCriterion("BEAR_NAME <", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameLessThanOrEqualTo(String value) {
            addCriterion("BEAR_NAME <=", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameLike(String value) {
            addCriterion("BEAR_NAME like", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameNotLike(String value) {
            addCriterion("BEAR_NAME not like", value, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameIn(List<String> values) {
            addCriterion("BEAR_NAME in", values, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameNotIn(List<String> values) {
            addCriterion("BEAR_NAME not in", values, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameBetween(String value1, String value2) {
            addCriterion("BEAR_NAME between", value1, value2, "bearName");
            return (Criteria) this;
        }

        public Criteria andBearNameNotBetween(String value1, String value2) {
            addCriterion("BEAR_NAME not between", value1, value2, "bearName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdIsNull() {
            addCriterion("NATIVE_PLACE_AREA_ID is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdIsNotNull() {
            addCriterion("NATIVE_PLACE_AREA_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID =", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdNotEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID <>", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdGreaterThan(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID >", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID >=", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdLessThan(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID <", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdLessThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID <=", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdLike(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID like", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdNotLike(String value) {
            addCriterion("NATIVE_PLACE_AREA_ID not like", value, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdIn(List<String> values) {
            addCriterion("NATIVE_PLACE_AREA_ID in", values, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdNotIn(List<String> values) {
            addCriterion("NATIVE_PLACE_AREA_ID not in", values, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_AREA_ID between", value1, value2, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaIdNotBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_AREA_ID not between", value1, value2, "nativePlaceAreaId");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameIsNull() {
            addCriterion("NATIVE_PLACE_PROV_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameIsNotNull() {
            addCriterion("NATIVE_PLACE_PROV_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME =", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameNotEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME <>", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameGreaterThan(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME >", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME >=", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameLessThan(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME <", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameLessThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME <=", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameLike(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME like", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameNotLike(String value) {
            addCriterion("NATIVE_PLACE_PROV_NAME not like", value, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameIn(List<String> values) {
            addCriterion("NATIVE_PLACE_PROV_NAME in", values, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameNotIn(List<String> values) {
            addCriterion("NATIVE_PLACE_PROV_NAME not in", values, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_PROV_NAME between", value1, value2, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceProvNameNotBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_PROV_NAME not between", value1, value2, "nativePlaceProvName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameIsNull() {
            addCriterion("NATIVE_PLACE_CITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameIsNotNull() {
            addCriterion("NATIVE_PLACE_CITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME =", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameNotEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME <>", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameGreaterThan(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME >", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME >=", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameLessThan(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME <", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameLessThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME <=", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameLike(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME like", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameNotLike(String value) {
            addCriterion("NATIVE_PLACE_CITY_NAME not like", value, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameIn(List<String> values) {
            addCriterion("NATIVE_PLACE_CITY_NAME in", values, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameNotIn(List<String> values) {
            addCriterion("NATIVE_PLACE_CITY_NAME not in", values, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_CITY_NAME between", value1, value2, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceCityNameNotBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_CITY_NAME not between", value1, value2, "nativePlaceCityName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameIsNull() {
            addCriterion("NATIVE_PLACE_AREA_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameIsNotNull() {
            addCriterion("NATIVE_PLACE_AREA_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME =", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameNotEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME <>", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameGreaterThan(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME >", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME >=", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameLessThan(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME <", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameLessThanOrEqualTo(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME <=", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameLike(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME like", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameNotLike(String value) {
            addCriterion("NATIVE_PLACE_AREA_NAME not like", value, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameIn(List<String> values) {
            addCriterion("NATIVE_PLACE_AREA_NAME in", values, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameNotIn(List<String> values) {
            addCriterion("NATIVE_PLACE_AREA_NAME not in", values, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_AREA_NAME between", value1, value2, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andNativePlaceAreaNameNotBetween(String value1, String value2) {
            addCriterion("NATIVE_PLACE_AREA_NAME not between", value1, value2, "nativePlaceAreaName");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNull() {
            addCriterion("LOCK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNotNull() {
            addCriterion("LOCK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLockTimeEqualTo(String value) {
            addCriterion("LOCK_TIME =", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotEqualTo(String value) {
            addCriterion("LOCK_TIME <>", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThan(String value) {
            addCriterion("LOCK_TIME >", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LOCK_TIME >=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThan(String value) {
            addCriterion("LOCK_TIME <", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThanOrEqualTo(String value) {
            addCriterion("LOCK_TIME <=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLike(String value) {
            addCriterion("LOCK_TIME like", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotLike(String value) {
            addCriterion("LOCK_TIME not like", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIn(List<String> values) {
            addCriterion("LOCK_TIME in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotIn(List<String> values) {
            addCriterion("LOCK_TIME not in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeBetween(String value1, String value2) {
            addCriterion("LOCK_TIME between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotBetween(String value1, String value2) {
            addCriterion("LOCK_TIME not between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaIsNull() {
            addCriterion("IS_EXAM_TEA is null");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaIsNotNull() {
            addCriterion("IS_EXAM_TEA is not null");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaEqualTo(String value) {
            addCriterion("IS_EXAM_TEA =", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotEqualTo(String value) {
            addCriterion("IS_EXAM_TEA <>", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaGreaterThan(String value) {
            addCriterion("IS_EXAM_TEA >", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaGreaterThanOrEqualTo(String value) {
            addCriterion("IS_EXAM_TEA >=", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaLessThan(String value) {
            addCriterion("IS_EXAM_TEA <", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaLessThanOrEqualTo(String value) {
            addCriterion("IS_EXAM_TEA <=", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaLike(String value) {
            addCriterion("IS_EXAM_TEA like", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotLike(String value) {
            addCriterion("IS_EXAM_TEA not like", value, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaIn(List<String> values) {
            addCriterion("IS_EXAM_TEA in", values, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotIn(List<String> values) {
            addCriterion("IS_EXAM_TEA not in", values, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaBetween(String value1, String value2) {
            addCriterion("IS_EXAM_TEA between", value1, value2, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsExamTeaNotBetween(String value1, String value2) {
            addCriterion("IS_EXAM_TEA not between", value1, value2, "isExamTea");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuIsNull() {
            addCriterion("IS_OWNER_STU is null");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuIsNotNull() {
            addCriterion("IS_OWNER_STU is not null");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuEqualTo(String value) {
            addCriterion("IS_OWNER_STU =", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuNotEqualTo(String value) {
            addCriterion("IS_OWNER_STU <>", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuGreaterThan(String value) {
            addCriterion("IS_OWNER_STU >", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OWNER_STU >=", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuLessThan(String value) {
            addCriterion("IS_OWNER_STU <", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuLessThanOrEqualTo(String value) {
            addCriterion("IS_OWNER_STU <=", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuLike(String value) {
            addCriterion("IS_OWNER_STU like", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuNotLike(String value) {
            addCriterion("IS_OWNER_STU not like", value, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuIn(List<String> values) {
            addCriterion("IS_OWNER_STU in", values, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuNotIn(List<String> values) {
            addCriterion("IS_OWNER_STU not in", values, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuBetween(String value1, String value2) {
            addCriterion("IS_OWNER_STU between", value1, value2, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andIsOwnerStuNotBetween(String value1, String value2) {
            addCriterion("IS_OWNER_STU not between", value1, value2, "isOwnerStu");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdIsNull() {
            addCriterion("LCJN_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdIsNotNull() {
            addCriterion("LCJN_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdEqualTo(String value) {
            addCriterion("LCJN_SPE_ID =", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdNotEqualTo(String value) {
            addCriterion("LCJN_SPE_ID <>", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdGreaterThan(String value) {
            addCriterion("LCJN_SPE_ID >", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("LCJN_SPE_ID >=", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdLessThan(String value) {
            addCriterion("LCJN_SPE_ID <", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdLessThanOrEqualTo(String value) {
            addCriterion("LCJN_SPE_ID <=", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdLike(String value) {
            addCriterion("LCJN_SPE_ID like", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdNotLike(String value) {
            addCriterion("LCJN_SPE_ID not like", value, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdIn(List<String> values) {
            addCriterion("LCJN_SPE_ID in", values, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdNotIn(List<String> values) {
            addCriterion("LCJN_SPE_ID not in", values, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdBetween(String value1, String value2) {
            addCriterion("LCJN_SPE_ID between", value1, value2, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeIdNotBetween(String value1, String value2) {
            addCriterion("LCJN_SPE_ID not between", value1, value2, "lcjnSpeId");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameIsNull() {
            addCriterion("LCJN_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameIsNotNull() {
            addCriterion("LCJN_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameEqualTo(String value) {
            addCriterion("LCJN_SPE_NAME =", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameNotEqualTo(String value) {
            addCriterion("LCJN_SPE_NAME <>", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameGreaterThan(String value) {
            addCriterion("LCJN_SPE_NAME >", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("LCJN_SPE_NAME >=", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameLessThan(String value) {
            addCriterion("LCJN_SPE_NAME <", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameLessThanOrEqualTo(String value) {
            addCriterion("LCJN_SPE_NAME <=", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameLike(String value) {
            addCriterion("LCJN_SPE_NAME like", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameNotLike(String value) {
            addCriterion("LCJN_SPE_NAME not like", value, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameIn(List<String> values) {
            addCriterion("LCJN_SPE_NAME in", values, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameNotIn(List<String> values) {
            addCriterion("LCJN_SPE_NAME not in", values, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameBetween(String value1, String value2) {
            addCriterion("LCJN_SPE_NAME between", value1, value2, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andLcjnSpeNameNotBetween(String value1, String value2) {
            addCriterion("LCJN_SPE_NAME not between", value1, value2, "lcjnSpeName");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeIsNull() {
            addCriterion("APP_LOGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeIsNotNull() {
            addCriterion("APP_LOGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeEqualTo(String value) {
            addCriterion("APP_LOGIN_TIME =", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeNotEqualTo(String value) {
            addCriterion("APP_LOGIN_TIME <>", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeGreaterThan(String value) {
            addCriterion("APP_LOGIN_TIME >", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APP_LOGIN_TIME >=", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeLessThan(String value) {
            addCriterion("APP_LOGIN_TIME <", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeLessThanOrEqualTo(String value) {
            addCriterion("APP_LOGIN_TIME <=", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeLike(String value) {
            addCriterion("APP_LOGIN_TIME like", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeNotLike(String value) {
            addCriterion("APP_LOGIN_TIME not like", value, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeIn(List<String> values) {
            addCriterion("APP_LOGIN_TIME in", values, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeNotIn(List<String> values) {
            addCriterion("APP_LOGIN_TIME not in", values, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeBetween(String value1, String value2) {
            addCriterion("APP_LOGIN_TIME between", value1, value2, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginTimeNotBetween(String value1, String value2) {
            addCriterion("APP_LOGIN_TIME not between", value1, value2, "appLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeIsNull() {
            addCriterion("WEB_LOGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeIsNotNull() {
            addCriterion("WEB_LOGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeEqualTo(String value) {
            addCriterion("WEB_LOGIN_TIME =", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeNotEqualTo(String value) {
            addCriterion("WEB_LOGIN_TIME <>", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeGreaterThan(String value) {
            addCriterion("WEB_LOGIN_TIME >", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeGreaterThanOrEqualTo(String value) {
            addCriterion("WEB_LOGIN_TIME >=", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeLessThan(String value) {
            addCriterion("WEB_LOGIN_TIME <", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeLessThanOrEqualTo(String value) {
            addCriterion("WEB_LOGIN_TIME <=", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeLike(String value) {
            addCriterion("WEB_LOGIN_TIME like", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeNotLike(String value) {
            addCriterion("WEB_LOGIN_TIME not like", value, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeIn(List<String> values) {
            addCriterion("WEB_LOGIN_TIME in", values, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeNotIn(List<String> values) {
            addCriterion("WEB_LOGIN_TIME not in", values, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeBetween(String value1, String value2) {
            addCriterion("WEB_LOGIN_TIME between", value1, value2, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWebLoginTimeNotBetween(String value1, String value2) {
            addCriterion("WEB_LOGIN_TIME not between", value1, value2, "webLoginTime");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNull() {
            addCriterion("WORK_ORG_NAME is null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIsNotNull() {
            addCriterion("WORK_ORG_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameEqualTo(String value) {
            addCriterion("WORK_ORG_NAME =", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <>", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThan(String value) {
            addCriterion("WORK_ORG_NAME >", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME >=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThan(String value) {
            addCriterion("WORK_ORG_NAME <", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLessThanOrEqualTo(String value) {
            addCriterion("WORK_ORG_NAME <=", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameLike(String value) {
            addCriterion("WORK_ORG_NAME like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotLike(String value) {
            addCriterion("WORK_ORG_NAME not like", value, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameIn(List<String> values) {
            addCriterion("WORK_ORG_NAME in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotIn(List<String> values) {
            addCriterion("WORK_ORG_NAME not in", values, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andWorkOrgNameNotBetween(String value1, String value2) {
            addCriterion("WORK_ORG_NAME not between", value1, value2, "workOrgName");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityIsNull() {
            addCriterion("REPORTING_AUTHORITY is null");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityIsNotNull() {
            addCriterion("REPORTING_AUTHORITY is not null");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityEqualTo(String value) {
            addCriterion("REPORTING_AUTHORITY =", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityNotEqualTo(String value) {
            addCriterion("REPORTING_AUTHORITY <>", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityGreaterThan(String value) {
            addCriterion("REPORTING_AUTHORITY >", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityGreaterThanOrEqualTo(String value) {
            addCriterion("REPORTING_AUTHORITY >=", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityLessThan(String value) {
            addCriterion("REPORTING_AUTHORITY <", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityLessThanOrEqualTo(String value) {
            addCriterion("REPORTING_AUTHORITY <=", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityLike(String value) {
            addCriterion("REPORTING_AUTHORITY like", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityNotLike(String value) {
            addCriterion("REPORTING_AUTHORITY not like", value, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityIn(List<String> values) {
            addCriterion("REPORTING_AUTHORITY in", values, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityNotIn(List<String> values) {
            addCriterion("REPORTING_AUTHORITY not in", values, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityBetween(String value1, String value2) {
            addCriterion("REPORTING_AUTHORITY between", value1, value2, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andReportingAuthorityNotBetween(String value1, String value2) {
            addCriterion("REPORTING_AUTHORITY not between", value1, value2, "reportingAuthority");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminIsNull() {
            addCriterion("IS_ORG_ADMIN is null");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminIsNotNull() {
            addCriterion("IS_ORG_ADMIN is not null");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminEqualTo(String value) {
            addCriterion("IS_ORG_ADMIN =", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminNotEqualTo(String value) {
            addCriterion("IS_ORG_ADMIN <>", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminGreaterThan(String value) {
            addCriterion("IS_ORG_ADMIN >", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminGreaterThanOrEqualTo(String value) {
            addCriterion("IS_ORG_ADMIN >=", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminLessThan(String value) {
            addCriterion("IS_ORG_ADMIN <", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminLessThanOrEqualTo(String value) {
            addCriterion("IS_ORG_ADMIN <=", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminLike(String value) {
            addCriterion("IS_ORG_ADMIN like", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminNotLike(String value) {
            addCriterion("IS_ORG_ADMIN not like", value, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminIn(List<String> values) {
            addCriterion("IS_ORG_ADMIN in", values, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminNotIn(List<String> values) {
            addCriterion("IS_ORG_ADMIN not in", values, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminBetween(String value1, String value2) {
            addCriterion("IS_ORG_ADMIN between", value1, value2, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andIsOrgAdminNotBetween(String value1, String value2) {
            addCriterion("IS_ORG_ADMIN not between", value1, value2, "isOrgAdmin");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIsNull() {
            addCriterion("CERTIFICATE_ID is null");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIsNotNull() {
            addCriterion("CERTIFICATE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateIdEqualTo(String value) {
            addCriterion("CERTIFICATE_ID =", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotEqualTo(String value) {
            addCriterion("CERTIFICATE_ID <>", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdGreaterThan(String value) {
            addCriterion("CERTIFICATE_ID >", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_ID >=", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLessThan(String value) {
            addCriterion("CERTIFICATE_ID <", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_ID <=", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdLike(String value) {
            addCriterion("CERTIFICATE_ID like", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotLike(String value) {
            addCriterion("CERTIFICATE_ID not like", value, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdIn(List<String> values) {
            addCriterion("CERTIFICATE_ID in", values, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotIn(List<String> values) {
            addCriterion("CERTIFICATE_ID not in", values, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_ID between", value1, value2, "certificateId");
            return (Criteria) this;
        }

        public Criteria andCertificateIdNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_ID not between", value1, value2, "certificateId");
            return (Criteria) this;
        }

        public Criteria andIsOscaIsNull() {
            addCriterion("IS_OSCA is null");
            return (Criteria) this;
        }

        public Criteria andIsOscaIsNotNull() {
            addCriterion("IS_OSCA is not null");
            return (Criteria) this;
        }

        public Criteria andIsOscaEqualTo(String value) {
            addCriterion("IS_OSCA =", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaNotEqualTo(String value) {
            addCriterion("IS_OSCA <>", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaGreaterThan(String value) {
            addCriterion("IS_OSCA >", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaGreaterThanOrEqualTo(String value) {
            addCriterion("IS_OSCA >=", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaLessThan(String value) {
            addCriterion("IS_OSCA <", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaLessThanOrEqualTo(String value) {
            addCriterion("IS_OSCA <=", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaLike(String value) {
            addCriterion("IS_OSCA like", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaNotLike(String value) {
            addCriterion("IS_OSCA not like", value, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaIn(List<String> values) {
            addCriterion("IS_OSCA in", values, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaNotIn(List<String> values) {
            addCriterion("IS_OSCA not in", values, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaBetween(String value1, String value2) {
            addCriterion("IS_OSCA between", value1, value2, "isOsca");
            return (Criteria) this;
        }

        public Criteria andIsOscaNotBetween(String value1, String value2) {
            addCriterion("IS_OSCA not between", value1, value2, "isOsca");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("ACCOUNT_NO is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("ACCOUNT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("ACCOUNT_NO =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("ACCOUNT_NO <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("ACCOUNT_NO >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("ACCOUNT_NO <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("ACCOUNT_NO <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("ACCOUNT_NO like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("ACCOUNT_NO not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("ACCOUNT_NO in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("ACCOUNT_NO not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("ACCOUNT_NO not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andWorkCodeIsNull() {
            addCriterion("WORK_CODE is null");
            return (Criteria) this;
        }

        public Criteria andWorkCodeIsNotNull() {
            addCriterion("WORK_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andWorkCodeEqualTo(String value) {
            addCriterion("WORK_CODE =", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeNotEqualTo(String value) {
            addCriterion("WORK_CODE <>", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeGreaterThan(String value) {
            addCriterion("WORK_CODE >", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeGreaterThanOrEqualTo(String value) {
            addCriterion("WORK_CODE >=", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeLessThan(String value) {
            addCriterion("WORK_CODE <", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeLessThanOrEqualTo(String value) {
            addCriterion("WORK_CODE <=", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeLike(String value) {
            addCriterion("WORK_CODE like", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeNotLike(String value) {
            addCriterion("WORK_CODE not like", value, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeIn(List<String> values) {
            addCriterion("WORK_CODE in", values, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeNotIn(List<String> values) {
            addCriterion("WORK_CODE not in", values, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeBetween(String value1, String value2) {
            addCriterion("WORK_CODE between", value1, value2, "workCode");
            return (Criteria) this;
        }

        public Criteria andWorkCodeNotBetween(String value1, String value2) {
            addCriterion("WORK_CODE not between", value1, value2, "workCode");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdIsNull() {
            addCriterion("RES_TRAINING_SPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdIsNotNull() {
            addCriterion("RES_TRAINING_SPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_ID =", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdNotEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_ID <>", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdGreaterThan(String value) {
            addCriterion("RES_TRAINING_SPE_ID >", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdGreaterThanOrEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_ID >=", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdLessThan(String value) {
            addCriterion("RES_TRAINING_SPE_ID <", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdLessThanOrEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_ID <=", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdLike(String value) {
            addCriterion("RES_TRAINING_SPE_ID like", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdNotLike(String value) {
            addCriterion("RES_TRAINING_SPE_ID not like", value, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdIn(List<String> values) {
            addCriterion("RES_TRAINING_SPE_ID in", values, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdNotIn(List<String> values) {
            addCriterion("RES_TRAINING_SPE_ID not in", values, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdBetween(String value1, String value2) {
            addCriterion("RES_TRAINING_SPE_ID between", value1, value2, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeIdNotBetween(String value1, String value2) {
            addCriterion("RES_TRAINING_SPE_ID not between", value1, value2, "resTrainingSpeId");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameIsNull() {
            addCriterion("RES_TRAINING_SPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameIsNotNull() {
            addCriterion("RES_TRAINING_SPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_NAME =", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameNotEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_NAME <>", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameGreaterThan(String value) {
            addCriterion("RES_TRAINING_SPE_NAME >", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameGreaterThanOrEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_NAME >=", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameLessThan(String value) {
            addCriterion("RES_TRAINING_SPE_NAME <", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameLessThanOrEqualTo(String value) {
            addCriterion("RES_TRAINING_SPE_NAME <=", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameLike(String value) {
            addCriterion("RES_TRAINING_SPE_NAME like", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameNotLike(String value) {
            addCriterion("RES_TRAINING_SPE_NAME not like", value, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameIn(List<String> values) {
            addCriterion("RES_TRAINING_SPE_NAME in", values, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameNotIn(List<String> values) {
            addCriterion("RES_TRAINING_SPE_NAME not in", values, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameBetween(String value1, String value2) {
            addCriterion("RES_TRAINING_SPE_NAME between", value1, value2, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andResTrainingSpeNameNotBetween(String value1, String value2) {
            addCriterion("RES_TRAINING_SPE_NAME not between", value1, value2, "resTrainingSpeName");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeIsNull() {
            addCriterion("PROMOTE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeIsNotNull() {
            addCriterion("PROMOTE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeEqualTo(String value) {
            addCriterion("PROMOTE_TIME =", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeNotEqualTo(String value) {
            addCriterion("PROMOTE_TIME <>", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeGreaterThan(String value) {
            addCriterion("PROMOTE_TIME >", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeGreaterThanOrEqualTo(String value) {
            addCriterion("PROMOTE_TIME >=", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeLessThan(String value) {
            addCriterion("PROMOTE_TIME <", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeLessThanOrEqualTo(String value) {
            addCriterion("PROMOTE_TIME <=", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeLike(String value) {
            addCriterion("PROMOTE_TIME like", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeNotLike(String value) {
            addCriterion("PROMOTE_TIME not like", value, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeIn(List<String> values) {
            addCriterion("PROMOTE_TIME in", values, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeNotIn(List<String> values) {
            addCriterion("PROMOTE_TIME not in", values, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeBetween(String value1, String value2) {
            addCriterion("PROMOTE_TIME between", value1, value2, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andPromoteTimeNotBetween(String value1, String value2) {
            addCriterion("PROMOTE_TIME not between", value1, value2, "promoteTime");
            return (Criteria) this;
        }

        public Criteria andUserMajorIsNull() {
            addCriterion("USER_MAJOR is null");
            return (Criteria) this;
        }

        public Criteria andUserMajorIsNotNull() {
            addCriterion("USER_MAJOR is not null");
            return (Criteria) this;
        }

        public Criteria andUserMajorEqualTo(String value) {
            addCriterion("USER_MAJOR =", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotEqualTo(String value) {
            addCriterion("USER_MAJOR <>", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorGreaterThan(String value) {
            addCriterion("USER_MAJOR >", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorGreaterThanOrEqualTo(String value) {
            addCriterion("USER_MAJOR >=", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorLessThan(String value) {
            addCriterion("USER_MAJOR <", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorLessThanOrEqualTo(String value) {
            addCriterion("USER_MAJOR <=", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorLike(String value) {
            addCriterion("USER_MAJOR like", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotLike(String value) {
            addCriterion("USER_MAJOR not like", value, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorIn(List<String> values) {
            addCriterion("USER_MAJOR in", values, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotIn(List<String> values) {
            addCriterion("USER_MAJOR not in", values, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorBetween(String value1, String value2) {
            addCriterion("USER_MAJOR between", value1, value2, "userMajor");
            return (Criteria) this;
        }

        public Criteria andUserMajorNotBetween(String value1, String value2) {
            addCriterion("USER_MAJOR not between", value1, value2, "userMajor");
            return (Criteria) this;
        }

        public Criteria andPassportNoIsNull() {
            addCriterion("PASSPORT_NO is null");
            return (Criteria) this;
        }

        public Criteria andPassportNoIsNotNull() {
            addCriterion("PASSPORT_NO is not null");
            return (Criteria) this;
        }

        public Criteria andPassportNoEqualTo(String value) {
            addCriterion("PASSPORT_NO =", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoNotEqualTo(String value) {
            addCriterion("PASSPORT_NO <>", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoGreaterThan(String value) {
            addCriterion("PASSPORT_NO >", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoGreaterThanOrEqualTo(String value) {
            addCriterion("PASSPORT_NO >=", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoLessThan(String value) {
            addCriterion("PASSPORT_NO <", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoLessThanOrEqualTo(String value) {
            addCriterion("PASSPORT_NO <=", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoLike(String value) {
            addCriterion("PASSPORT_NO like", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoNotLike(String value) {
            addCriterion("PASSPORT_NO not like", value, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoIn(List<String> values) {
            addCriterion("PASSPORT_NO in", values, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoNotIn(List<String> values) {
            addCriterion("PASSPORT_NO not in", values, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoBetween(String value1, String value2) {
            addCriterion("PASSPORT_NO between", value1, value2, "passportNo");
            return (Criteria) this;
        }

        public Criteria andPassportNoNotBetween(String value1, String value2) {
            addCriterion("PASSPORT_NO not between", value1, value2, "passportNo");
            return (Criteria) this;
        }

        public Criteria andNationalityIdIsNull() {
            addCriterion("NATIONALITY_ID is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIdIsNotNull() {
            addCriterion("NATIONALITY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityIdEqualTo(String value) {
            addCriterion("NATIONALITY_ID =", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdNotEqualTo(String value) {
            addCriterion("NATIONALITY_ID <>", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdGreaterThan(String value) {
            addCriterion("NATIONALITY_ID >", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdGreaterThanOrEqualTo(String value) {
            addCriterion("NATIONALITY_ID >=", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdLessThan(String value) {
            addCriterion("NATIONALITY_ID <", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdLessThanOrEqualTo(String value) {
            addCriterion("NATIONALITY_ID <=", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdLike(String value) {
            addCriterion("NATIONALITY_ID like", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdNotLike(String value) {
            addCriterion("NATIONALITY_ID not like", value, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdIn(List<String> values) {
            addCriterion("NATIONALITY_ID in", values, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdNotIn(List<String> values) {
            addCriterion("NATIONALITY_ID not in", values, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdBetween(String value1, String value2) {
            addCriterion("NATIONALITY_ID between", value1, value2, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityIdNotBetween(String value1, String value2) {
            addCriterion("NATIONALITY_ID not between", value1, value2, "nationalityId");
            return (Criteria) this;
        }

        public Criteria andNationalityNameIsNull() {
            addCriterion("NATIONALITY_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNationalityNameIsNotNull() {
            addCriterion("NATIONALITY_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityNameEqualTo(String value) {
            addCriterion("NATIONALITY_NAME =", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotEqualTo(String value) {
            addCriterion("NATIONALITY_NAME <>", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameGreaterThan(String value) {
            addCriterion("NATIONALITY_NAME >", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameGreaterThanOrEqualTo(String value) {
            addCriterion("NATIONALITY_NAME >=", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameLessThan(String value) {
            addCriterion("NATIONALITY_NAME <", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameLessThanOrEqualTo(String value) {
            addCriterion("NATIONALITY_NAME <=", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameLike(String value) {
            addCriterion("NATIONALITY_NAME like", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotLike(String value) {
            addCriterion("NATIONALITY_NAME not like", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameIn(List<String> values) {
            addCriterion("NATIONALITY_NAME in", values, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotIn(List<String> values) {
            addCriterion("NATIONALITY_NAME not in", values, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameBetween(String value1, String value2) {
            addCriterion("NATIONALITY_NAME between", value1, value2, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotBetween(String value1, String value2) {
            addCriterion("NATIONALITY_NAME not between", value1, value2, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andIsForeignIsNull() {
            addCriterion("IS_FOREIGN is null");
            return (Criteria) this;
        }

        public Criteria andIsForeignIsNotNull() {
            addCriterion("IS_FOREIGN is not null");
            return (Criteria) this;
        }

        public Criteria andIsForeignEqualTo(String value) {
            addCriterion("IS_FOREIGN =", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignNotEqualTo(String value) {
            addCriterion("IS_FOREIGN <>", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignGreaterThan(String value) {
            addCriterion("IS_FOREIGN >", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignGreaterThanOrEqualTo(String value) {
            addCriterion("IS_FOREIGN >=", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignLessThan(String value) {
            addCriterion("IS_FOREIGN <", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignLessThanOrEqualTo(String value) {
            addCriterion("IS_FOREIGN <=", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignLike(String value) {
            addCriterion("IS_FOREIGN like", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignNotLike(String value) {
            addCriterion("IS_FOREIGN not like", value, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignIn(List<String> values) {
            addCriterion("IS_FOREIGN in", values, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignNotIn(List<String> values) {
            addCriterion("IS_FOREIGN not in", values, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignBetween(String value1, String value2) {
            addCriterion("IS_FOREIGN between", value1, value2, "isForeign");
            return (Criteria) this;
        }

        public Criteria andIsForeignNotBetween(String value1, String value2) {
            addCriterion("IS_FOREIGN not between", value1, value2, "isForeign");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("USER_ID like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("USER_ID not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserPinyinIsNull() {
            addCriterion("USER_PINYIN is null");
            return (Criteria) this;
        }

        public Criteria andUserPinyinIsNotNull() {
            addCriterion("USER_PINYIN is not null");
            return (Criteria) this;
        }

        public Criteria andUserPinyinEqualTo(String value) {
            addCriterion("USER_PINYIN =", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinNotEqualTo(String value) {
            addCriterion("USER_PINYIN <>", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinGreaterThan(String value) {
            addCriterion("USER_PINYIN >", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinGreaterThanOrEqualTo(String value) {
            addCriterion("USER_PINYIN >=", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinLessThan(String value) {
            addCriterion("USER_PINYIN <", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinLessThanOrEqualTo(String value) {
            addCriterion("USER_PINYIN <=", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinLike(String value) {
            addCriterion("USER_PINYIN like", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinNotLike(String value) {
            addCriterion("USER_PINYIN not like", value, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinIn(List<String> values) {
            addCriterion("USER_PINYIN in", values, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinNotIn(List<String> values) {
            addCriterion("USER_PINYIN not in", values, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinBetween(String value1, String value2) {
            addCriterion("USER_PINYIN between", value1, value2, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andUserPinyinNotBetween(String value1, String value2) {
            addCriterion("USER_PINYIN not between", value1, value2, "userPinyin");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdIsNull() {
            addCriterion("CERTIFICATE_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdIsNotNull() {
            addCriterion("CERTIFICATE_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID =", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID <>", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdGreaterThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID >", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID >=", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdLessThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID <", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID <=", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID like", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_ID not like", value, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_ID in", values, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_ID not in", values, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_ID between", value1, value2, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelIdNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_ID not between", value1, value2, "certificateLevelId");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameIsNull() {
            addCriterion("CERTIFICATE_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameIsNotNull() {
            addCriterion("CERTIFICATE_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME =", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME <>", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameGreaterThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME >", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME >=", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameLessThan(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME <", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME <=", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME like", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotLike(String value) {
            addCriterion("CERTIFICATE_LEVEL_NAME not like", value, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_NAME in", values, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotIn(List<String> values) {
            addCriterion("CERTIFICATE_LEVEL_NAME not in", values, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_NAME between", value1, value2, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateLevelNameNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_LEVEL_NAME not between", value1, value2, "certificateLevelName");
            return (Criteria) this;
        }

        public Criteria andCertificateFileIsNull() {
            addCriterion("CERTIFICATE_FILE is null");
            return (Criteria) this;
        }

        public Criteria andCertificateFileIsNotNull() {
            addCriterion("CERTIFICATE_FILE is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateFileEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE =", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE <>", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileGreaterThan(String value) {
            addCriterion("CERTIFICATE_FILE >", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE >=", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileLessThan(String value) {
            addCriterion("CERTIFICATE_FILE <", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_FILE <=", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileLike(String value) {
            addCriterion("CERTIFICATE_FILE like", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotLike(String value) {
            addCriterion("CERTIFICATE_FILE not like", value, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileIn(List<String> values) {
            addCriterion("CERTIFICATE_FILE in", values, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotIn(List<String> values) {
            addCriterion("CERTIFICATE_FILE not in", values, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FILE between", value1, value2, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateFileNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_FILE not between", value1, value2, "certificateFile");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIsNull() {
            addCriterion("CERTIFICATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIsNotNull() {
            addCriterion("CERTIFICATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME =", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME <>", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeGreaterThan(String value) {
            addCriterion("CERTIFICATE_TIME >", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME >=", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLessThan(String value) {
            addCriterion("CERTIFICATE_TIME <", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLessThanOrEqualTo(String value) {
            addCriterion("CERTIFICATE_TIME <=", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeLike(String value) {
            addCriterion("CERTIFICATE_TIME like", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotLike(String value) {
            addCriterion("CERTIFICATE_TIME not like", value, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeIn(List<String> values) {
            addCriterion("CERTIFICATE_TIME in", values, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotIn(List<String> values) {
            addCriterion("CERTIFICATE_TIME not in", values, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_TIME between", value1, value2, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andCertificateTimeNotBetween(String value1, String value2) {
            addCriterion("CERTIFICATE_TIME not between", value1, value2, "certificateTime");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIsNull() {
            addCriterion("PARTY_BRANCH_ID is null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIsNotNull() {
            addCriterion("PARTY_BRANCH_ID is not null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID =", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID <>", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdGreaterThan(String value) {
            addCriterion("PARTY_BRANCH_ID >", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdGreaterThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID >=", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLessThan(String value) {
            addCriterion("PARTY_BRANCH_ID <", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLessThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_ID <=", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdLike(String value) {
            addCriterion("PARTY_BRANCH_ID like", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotLike(String value) {
            addCriterion("PARTY_BRANCH_ID not like", value, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdIn(List<String> values) {
            addCriterion("PARTY_BRANCH_ID in", values, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotIn(List<String> values) {
            addCriterion("PARTY_BRANCH_ID not in", values, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_ID between", value1, value2, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchIdNotBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_ID not between", value1, value2, "partyBranchId");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIsNull() {
            addCriterion("PARTY_BRANCH_NAME is null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIsNotNull() {
            addCriterion("PARTY_BRANCH_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME =", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME <>", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameGreaterThan(String value) {
            addCriterion("PARTY_BRANCH_NAME >", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME >=", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLessThan(String value) {
            addCriterion("PARTY_BRANCH_NAME <", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLessThanOrEqualTo(String value) {
            addCriterion("PARTY_BRANCH_NAME <=", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameLike(String value) {
            addCriterion("PARTY_BRANCH_NAME like", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotLike(String value) {
            addCriterion("PARTY_BRANCH_NAME not like", value, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameIn(List<String> values) {
            addCriterion("PARTY_BRANCH_NAME in", values, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotIn(List<String> values) {
            addCriterion("PARTY_BRANCH_NAME not in", values, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_NAME between", value1, value2, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andPartyBranchNameNotBetween(String value1, String value2) {
            addCriterion("PARTY_BRANCH_NAME not between", value1, value2, "partyBranchName");
            return (Criteria) this;
        }

        public Criteria andTipPasswordIsNull() {
            addCriterion("TIP_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andTipPasswordIsNotNull() {
            addCriterion("TIP_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andTipPasswordEqualTo(String value) {
            addCriterion("TIP_PASSWORD =", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordNotEqualTo(String value) {
            addCriterion("TIP_PASSWORD <>", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordGreaterThan(String value) {
            addCriterion("TIP_PASSWORD >", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("TIP_PASSWORD >=", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordLessThan(String value) {
            addCriterion("TIP_PASSWORD <", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordLessThanOrEqualTo(String value) {
            addCriterion("TIP_PASSWORD <=", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordLike(String value) {
            addCriterion("TIP_PASSWORD like", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordNotLike(String value) {
            addCriterion("TIP_PASSWORD not like", value, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordIn(List<String> values) {
            addCriterion("TIP_PASSWORD in", values, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordNotIn(List<String> values) {
            addCriterion("TIP_PASSWORD not in", values, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordBetween(String value1, String value2) {
            addCriterion("TIP_PASSWORD between", value1, value2, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andTipPasswordNotBetween(String value1, String value2) {
            addCriterion("TIP_PASSWORD not between", value1, value2, "tipPassword");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdIsNull() {
            addCriterion("MEDICINE_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdIsNotNull() {
            addCriterion("MEDICINE_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_ID =", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdNotEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_ID <>", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdGreaterThan(String value) {
            addCriterion("MEDICINE_TYPE_ID >", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_ID >=", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdLessThan(String value) {
            addCriterion("MEDICINE_TYPE_ID <", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdLessThanOrEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_ID <=", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdLike(String value) {
            addCriterion("MEDICINE_TYPE_ID like", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdNotLike(String value) {
            addCriterion("MEDICINE_TYPE_ID not like", value, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdIn(List<String> values) {
            addCriterion("MEDICINE_TYPE_ID in", values, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdNotIn(List<String> values) {
            addCriterion("MEDICINE_TYPE_ID not in", values, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdBetween(String value1, String value2) {
            addCriterion("MEDICINE_TYPE_ID between", value1, value2, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeIdNotBetween(String value1, String value2) {
            addCriterion("MEDICINE_TYPE_ID not between", value1, value2, "medicineTypeId");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameIsNull() {
            addCriterion("MEDICINE_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameIsNotNull() {
            addCriterion("MEDICINE_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_NAME =", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameNotEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_NAME <>", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameGreaterThan(String value) {
            addCriterion("MEDICINE_TYPE_NAME >", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_NAME >=", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameLessThan(String value) {
            addCriterion("MEDICINE_TYPE_NAME <", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameLessThanOrEqualTo(String value) {
            addCriterion("MEDICINE_TYPE_NAME <=", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameLike(String value) {
            addCriterion("MEDICINE_TYPE_NAME like", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameNotLike(String value) {
            addCriterion("MEDICINE_TYPE_NAME not like", value, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameIn(List<String> values) {
            addCriterion("MEDICINE_TYPE_NAME in", values, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameNotIn(List<String> values) {
            addCriterion("MEDICINE_TYPE_NAME not in", values, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameBetween(String value1, String value2) {
            addCriterion("MEDICINE_TYPE_NAME between", value1, value2, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andMedicineTypeNameNotBetween(String value1, String value2) {
            addCriterion("MEDICINE_TYPE_NAME not between", value1, value2, "medicineTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdIsNull() {
            addCriterion("TEACHER_TYPE_ID is null");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdIsNotNull() {
            addCriterion("TEACHER_TYPE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdEqualTo(String value) {
            addCriterion("TEACHER_TYPE_ID =", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdNotEqualTo(String value) {
            addCriterion("TEACHER_TYPE_ID <>", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdGreaterThan(String value) {
            addCriterion("TEACHER_TYPE_ID >", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_TYPE_ID >=", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdLessThan(String value) {
            addCriterion("TEACHER_TYPE_ID <", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_TYPE_ID <=", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdLike(String value) {
            addCriterion("TEACHER_TYPE_ID like", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdNotLike(String value) {
            addCriterion("TEACHER_TYPE_ID not like", value, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdIn(List<String> values) {
            addCriterion("TEACHER_TYPE_ID in", values, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdNotIn(List<String> values) {
            addCriterion("TEACHER_TYPE_ID not in", values, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdBetween(String value1, String value2) {
            addCriterion("TEACHER_TYPE_ID between", value1, value2, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeIdNotBetween(String value1, String value2) {
            addCriterion("TEACHER_TYPE_ID not between", value1, value2, "teacherTypeId");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameIsNull() {
            addCriterion("TEACHER_TYPE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameIsNotNull() {
            addCriterion("TEACHER_TYPE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameEqualTo(String value) {
            addCriterion("TEACHER_TYPE_NAME =", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameNotEqualTo(String value) {
            addCriterion("TEACHER_TYPE_NAME <>", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameGreaterThan(String value) {
            addCriterion("TEACHER_TYPE_NAME >", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("TEACHER_TYPE_NAME >=", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameLessThan(String value) {
            addCriterion("TEACHER_TYPE_NAME <", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameLessThanOrEqualTo(String value) {
            addCriterion("TEACHER_TYPE_NAME <=", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameLike(String value) {
            addCriterion("TEACHER_TYPE_NAME like", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameNotLike(String value) {
            addCriterion("TEACHER_TYPE_NAME not like", value, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameIn(List<String> values) {
            addCriterion("TEACHER_TYPE_NAME in", values, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameNotIn(List<String> values) {
            addCriterion("TEACHER_TYPE_NAME not in", values, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameBetween(String value1, String value2) {
            addCriterion("TEACHER_TYPE_NAME between", value1, value2, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andTeacherTypeNameNotBetween(String value1, String value2) {
            addCriterion("TEACHER_TYPE_NAME not between", value1, value2, "teacherTypeName");
            return (Criteria) this;
        }

        public Criteria andBaseFlowIsNull() {
            addCriterion("BASE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andBaseFlowIsNotNull() {
            addCriterion("BASE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andBaseFlowEqualTo(String value) {
            addCriterion("BASE_FLOW =", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowNotEqualTo(String value) {
            addCriterion("BASE_FLOW <>", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowGreaterThan(String value) {
            addCriterion("BASE_FLOW >", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_FLOW >=", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowLessThan(String value) {
            addCriterion("BASE_FLOW <", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowLessThanOrEqualTo(String value) {
            addCriterion("BASE_FLOW <=", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowLike(String value) {
            addCriterion("BASE_FLOW like", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowNotLike(String value) {
            addCriterion("BASE_FLOW not like", value, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowIn(List<String> values) {
            addCriterion("BASE_FLOW in", values, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowNotIn(List<String> values) {
            addCriterion("BASE_FLOW not in", values, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowBetween(String value1, String value2) {
            addCriterion("BASE_FLOW between", value1, value2, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseFlowNotBetween(String value1, String value2) {
            addCriterion("BASE_FLOW not between", value1, value2, "baseFlow");
            return (Criteria) this;
        }

        public Criteria andBaseNameIsNull() {
            addCriterion("BASE_NAME is null");
            return (Criteria) this;
        }

        public Criteria andBaseNameIsNotNull() {
            addCriterion("BASE_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andBaseNameEqualTo(String value) {
            addCriterion("BASE_NAME =", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameNotEqualTo(String value) {
            addCriterion("BASE_NAME <>", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameGreaterThan(String value) {
            addCriterion("BASE_NAME >", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_NAME >=", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameLessThan(String value) {
            addCriterion("BASE_NAME <", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameLessThanOrEqualTo(String value) {
            addCriterion("BASE_NAME <=", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameLike(String value) {
            addCriterion("BASE_NAME like", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameNotLike(String value) {
            addCriterion("BASE_NAME not like", value, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameIn(List<String> values) {
            addCriterion("BASE_NAME in", values, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameNotIn(List<String> values) {
            addCriterion("BASE_NAME not in", values, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameBetween(String value1, String value2) {
            addCriterion("BASE_NAME between", value1, value2, "baseName");
            return (Criteria) this;
        }

        public Criteria andBaseNameNotBetween(String value1, String value2) {
            addCriterion("BASE_NAME not between", value1, value2, "baseName");
            return (Criteria) this;
        }

        public Criteria andIsVerifyIsNull() {
            addCriterion("IS_VERIFY is null");
            return (Criteria) this;
        }

        public Criteria andIsVerifyIsNotNull() {
            addCriterion("IS_VERIFY is not null");
            return (Criteria) this;
        }

        public Criteria andIsVerifyEqualTo(String value) {
            addCriterion("IS_VERIFY =", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyNotEqualTo(String value) {
            addCriterion("IS_VERIFY <>", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyGreaterThan(String value) {
            addCriterion("IS_VERIFY >", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyGreaterThanOrEqualTo(String value) {
            addCriterion("IS_VERIFY >=", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyLessThan(String value) {
            addCriterion("IS_VERIFY <", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyLessThanOrEqualTo(String value) {
            addCriterion("IS_VERIFY <=", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyLike(String value) {
            addCriterion("IS_VERIFY like", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyNotLike(String value) {
            addCriterion("IS_VERIFY not like", value, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyIn(List<String> values) {
            addCriterion("IS_VERIFY in", values, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyNotIn(List<String> values) {
            addCriterion("IS_VERIFY not in", values, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyBetween(String value1, String value2) {
            addCriterion("IS_VERIFY between", value1, value2, "isVerify");
            return (Criteria) this;
        }

        public Criteria andIsVerifyNotBetween(String value1, String value2) {
            addCriterion("IS_VERIFY not between", value1, value2, "isVerify");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeIsNull() {
            addCriterion("VERIFY_CODE is null");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeIsNotNull() {
            addCriterion("VERIFY_CODE is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeEqualTo(String value) {
            addCriterion("VERIFY_CODE =", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeNotEqualTo(String value) {
            addCriterion("VERIFY_CODE <>", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeGreaterThan(String value) {
            addCriterion("VERIFY_CODE >", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("VERIFY_CODE >=", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeLessThan(String value) {
            addCriterion("VERIFY_CODE <", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeLessThanOrEqualTo(String value) {
            addCriterion("VERIFY_CODE <=", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeLike(String value) {
            addCriterion("VERIFY_CODE like", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeNotLike(String value) {
            addCriterion("VERIFY_CODE not like", value, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeIn(List<String> values) {
            addCriterion("VERIFY_CODE in", values, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeNotIn(List<String> values) {
            addCriterion("VERIFY_CODE not in", values, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeBetween(String value1, String value2) {
            addCriterion("VERIFY_CODE between", value1, value2, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeNotBetween(String value1, String value2) {
            addCriterion("VERIFY_CODE not between", value1, value2, "verifyCode");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeIsNull() {
            addCriterion("VERIFY_CODE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeIsNotNull() {
            addCriterion("VERIFY_CODE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeEqualTo(String value) {
            addCriterion("VERIFY_CODE_TIME =", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeNotEqualTo(String value) {
            addCriterion("VERIFY_CODE_TIME <>", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeGreaterThan(String value) {
            addCriterion("VERIFY_CODE_TIME >", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeGreaterThanOrEqualTo(String value) {
            addCriterion("VERIFY_CODE_TIME >=", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeLessThan(String value) {
            addCriterion("VERIFY_CODE_TIME <", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeLessThanOrEqualTo(String value) {
            addCriterion("VERIFY_CODE_TIME <=", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeLike(String value) {
            addCriterion("VERIFY_CODE_TIME like", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeNotLike(String value) {
            addCriterion("VERIFY_CODE_TIME not like", value, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeIn(List<String> values) {
            addCriterion("VERIFY_CODE_TIME in", values, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeNotIn(List<String> values) {
            addCriterion("VERIFY_CODE_TIME not in", values, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeBetween(String value1, String value2) {
            addCriterion("VERIFY_CODE_TIME between", value1, value2, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andVerifyCodeTimeNotBetween(String value1, String value2) {
            addCriterion("VERIFY_CODE_TIME not between", value1, value2, "verifyCodeTime");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNull() {
            addCriterion("IS_SEND is null");
            return (Criteria) this;
        }

        public Criteria andIsSendIsNotNull() {
            addCriterion("IS_SEND is not null");
            return (Criteria) this;
        }

        public Criteria andIsSendEqualTo(String value) {
            addCriterion("IS_SEND =", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotEqualTo(String value) {
            addCriterion("IS_SEND <>", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThan(String value) {
            addCriterion("IS_SEND >", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SEND >=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThan(String value) {
            addCriterion("IS_SEND <", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLessThanOrEqualTo(String value) {
            addCriterion("IS_SEND <=", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendLike(String value) {
            addCriterion("IS_SEND like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotLike(String value) {
            addCriterion("IS_SEND not like", value, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendIn(List<String> values) {
            addCriterion("IS_SEND in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotIn(List<String> values) {
            addCriterion("IS_SEND not in", values, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendBetween(String value1, String value2) {
            addCriterion("IS_SEND between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andIsSendNotBetween(String value1, String value2) {
            addCriterion("IS_SEND not between", value1, value2, "isSend");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgIsNull() {
            addCriterion("IDCARD_FRONT_IMG is null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgIsNotNull() {
            addCriterion("IDCARD_FRONT_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgEqualTo(String value) {
            addCriterion("IDCARD_FRONT_IMG =", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgNotEqualTo(String value) {
            addCriterion("IDCARD_FRONT_IMG <>", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgGreaterThan(String value) {
            addCriterion("IDCARD_FRONT_IMG >", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgGreaterThanOrEqualTo(String value) {
            addCriterion("IDCARD_FRONT_IMG >=", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgLessThan(String value) {
            addCriterion("IDCARD_FRONT_IMG <", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgLessThanOrEqualTo(String value) {
            addCriterion("IDCARD_FRONT_IMG <=", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgLike(String value) {
            addCriterion("IDCARD_FRONT_IMG like", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgNotLike(String value) {
            addCriterion("IDCARD_FRONT_IMG not like", value, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgIn(List<String> values) {
            addCriterion("IDCARD_FRONT_IMG in", values, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgNotIn(List<String> values) {
            addCriterion("IDCARD_FRONT_IMG not in", values, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgBetween(String value1, String value2) {
            addCriterion("IDCARD_FRONT_IMG between", value1, value2, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardFrontImgNotBetween(String value1, String value2) {
            addCriterion("IDCARD_FRONT_IMG not between", value1, value2, "idcardFrontImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgIsNull() {
            addCriterion("IDCARD_OPPOSITE_IMG is null");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgIsNotNull() {
            addCriterion("IDCARD_OPPOSITE_IMG is not null");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgEqualTo(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG =", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgNotEqualTo(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG <>", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgGreaterThan(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG >", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgGreaterThanOrEqualTo(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG >=", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgLessThan(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG <", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgLessThanOrEqualTo(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG <=", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgLike(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG like", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgNotLike(String value) {
            addCriterion("IDCARD_OPPOSITE_IMG not like", value, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgIn(List<String> values) {
            addCriterion("IDCARD_OPPOSITE_IMG in", values, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgNotIn(List<String> values) {
            addCriterion("IDCARD_OPPOSITE_IMG not in", values, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgBetween(String value1, String value2) {
            addCriterion("IDCARD_OPPOSITE_IMG between", value1, value2, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andIdcardOppositeImgNotBetween(String value1, String value2) {
            addCriterion("IDCARD_OPPOSITE_IMG not between", value1, value2, "idcardOppositeImg");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("NATIONALITY is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("NATIONALITY is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(String value) {
            addCriterion("NATIONALITY =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(String value) {
            addCriterion("NATIONALITY <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(String value) {
            addCriterion("NATIONALITY >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(String value) {
            addCriterion("NATIONALITY >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(String value) {
            addCriterion("NATIONALITY <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(String value) {
            addCriterion("NATIONALITY <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLike(String value) {
            addCriterion("NATIONALITY like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotLike(String value) {
            addCriterion("NATIONALITY not like", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<String> values) {
            addCriterion("NATIONALITY in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<String> values) {
            addCriterion("NATIONALITY not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(String value1, String value2) {
            addCriterion("NATIONALITY between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(String value1, String value2) {
            addCriterion("NATIONALITY not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeIsNull() {
            addCriterion("LAST_LOGIN_ERROR_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeIsNotNull() {
            addCriterion("LAST_LOGIN_ERROR_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeEqualTo(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME =", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeNotEqualTo(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME <>", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeGreaterThan(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME >", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeGreaterThanOrEqualTo(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME >=", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeLessThan(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME <", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeLessThanOrEqualTo(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME <=", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeLike(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME like", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeNotLike(String value) {
            addCriterion("LAST_LOGIN_ERROR_TIME not like", value, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeIn(List<String> values) {
            addCriterion("LAST_LOGIN_ERROR_TIME in", values, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeNotIn(List<String> values) {
            addCriterion("LAST_LOGIN_ERROR_TIME not in", values, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeBetween(String value1, String value2) {
            addCriterion("LAST_LOGIN_ERROR_TIME between", value1, value2, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginErrorTimeNotBetween(String value1, String value2) {
            addCriterion("LAST_LOGIN_ERROR_TIME not between", value1, value2, "lastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountIsNull() {
            addCriterion("LOGIN_ERROR_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountIsNotNull() {
            addCriterion("LOGIN_ERROR_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountEqualTo(String value) {
            addCriterion("LOGIN_ERROR_COUNT =", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotEqualTo(String value) {
            addCriterion("LOGIN_ERROR_COUNT <>", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountGreaterThan(String value) {
            addCriterion("LOGIN_ERROR_COUNT >", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_ERROR_COUNT >=", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountLessThan(String value) {
            addCriterion("LOGIN_ERROR_COUNT <", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_ERROR_COUNT <=", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountLike(String value) {
            addCriterion("LOGIN_ERROR_COUNT like", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotLike(String value) {
            addCriterion("LOGIN_ERROR_COUNT not like", value, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountIn(List<String> values) {
            addCriterion("LOGIN_ERROR_COUNT in", values, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotIn(List<String> values) {
            addCriterion("LOGIN_ERROR_COUNT not in", values, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountBetween(String value1, String value2) {
            addCriterion("LOGIN_ERROR_COUNT between", value1, value2, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andLoginErrorCountNotBetween(String value1, String value2) {
            addCriterion("LOGIN_ERROR_COUNT not between", value1, value2, "loginErrorCount");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeIsNull() {
            addCriterion("CHANGE_PASSWORD_TIME is null");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeIsNotNull() {
            addCriterion("CHANGE_PASSWORD_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeEqualTo(String value) {
            addCriterion("CHANGE_PASSWORD_TIME =", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeNotEqualTo(String value) {
            addCriterion("CHANGE_PASSWORD_TIME <>", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeGreaterThan(String value) {
            addCriterion("CHANGE_PASSWORD_TIME >", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeGreaterThanOrEqualTo(String value) {
            addCriterion("CHANGE_PASSWORD_TIME >=", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeLessThan(String value) {
            addCriterion("CHANGE_PASSWORD_TIME <", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeLessThanOrEqualTo(String value) {
            addCriterion("CHANGE_PASSWORD_TIME <=", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeLike(String value) {
            addCriterion("CHANGE_PASSWORD_TIME like", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeNotLike(String value) {
            addCriterion("CHANGE_PASSWORD_TIME not like", value, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeIn(List<String> values) {
            addCriterion("CHANGE_PASSWORD_TIME in", values, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeNotIn(List<String> values) {
            addCriterion("CHANGE_PASSWORD_TIME not in", values, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeBetween(String value1, String value2) {
            addCriterion("CHANGE_PASSWORD_TIME between", value1, value2, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andChangePasswordTimeNotBetween(String value1, String value2) {
            addCriterion("CHANGE_PASSWORD_TIME not between", value1, value2, "changePasswordTime");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdIsNull() {
            addCriterion("IS_SUBMIT_ID is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdIsNotNull() {
            addCriterion("IS_SUBMIT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID =", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID <>", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdGreaterThan(String value) {
            addCriterion("IS_SUBMIT_ID >", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID >=", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdLessThan(String value) {
            addCriterion("IS_SUBMIT_ID <", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_ID <=", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdLike(String value) {
            addCriterion("IS_SUBMIT_ID like", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotLike(String value) {
            addCriterion("IS_SUBMIT_ID not like", value, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdIn(List<String> values) {
            addCriterion("IS_SUBMIT_ID in", values, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotIn(List<String> values) {
            addCriterion("IS_SUBMIT_ID not in", values, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_ID between", value1, value2, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitIdNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_ID not between", value1, value2, "isSubmitId");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameIsNull() {
            addCriterion("IS_SUBMIT_NAME is null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameIsNotNull() {
            addCriterion("IS_SUBMIT_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME =", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME <>", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameGreaterThan(String value) {
            addCriterion("IS_SUBMIT_NAME >", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME >=", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameLessThan(String value) {
            addCriterion("IS_SUBMIT_NAME <", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameLessThanOrEqualTo(String value) {
            addCriterion("IS_SUBMIT_NAME <=", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameLike(String value) {
            addCriterion("IS_SUBMIT_NAME like", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotLike(String value) {
            addCriterion("IS_SUBMIT_NAME not like", value, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameIn(List<String> values) {
            addCriterion("IS_SUBMIT_NAME in", values, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotIn(List<String> values) {
            addCriterion("IS_SUBMIT_NAME not in", values, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_NAME between", value1, value2, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andIsSubmitNameNotBetween(String value1, String value2) {
            addCriterion("IS_SUBMIT_NAME not between", value1, value2, "isSubmitName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIsNull() {
            addCriterion("CHECK_STATUS_ID is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIsNotNull() {
            addCriterion("CHECK_STATUS_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID =", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID <>", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdGreaterThan(String value) {
            addCriterion("CHECK_STATUS_ID >", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID >=", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLessThan(String value) {
            addCriterion("CHECK_STATUS_ID <", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_ID <=", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdLike(String value) {
            addCriterion("CHECK_STATUS_ID like", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotLike(String value) {
            addCriterion("CHECK_STATUS_ID not like", value, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdIn(List<String> values) {
            addCriterion("CHECK_STATUS_ID in", values, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotIn(List<String> values) {
            addCriterion("CHECK_STATUS_ID not in", values, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_ID between", value1, value2, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIdNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_ID not between", value1, value2, "checkStatusId");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIsNull() {
            addCriterion("CHECK_STATUS_NAME is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIsNotNull() {
            addCriterion("CHECK_STATUS_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME =", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME <>", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameGreaterThan(String value) {
            addCriterion("CHECK_STATUS_NAME >", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME >=", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLessThan(String value) {
            addCriterion("CHECK_STATUS_NAME <", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLessThanOrEqualTo(String value) {
            addCriterion("CHECK_STATUS_NAME <=", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameLike(String value) {
            addCriterion("CHECK_STATUS_NAME like", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotLike(String value) {
            addCriterion("CHECK_STATUS_NAME not like", value, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameIn(List<String> values) {
            addCriterion("CHECK_STATUS_NAME in", values, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotIn(List<String> values) {
            addCriterion("CHECK_STATUS_NAME not in", values, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_NAME between", value1, value2, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNameNotBetween(String value1, String value2) {
            addCriterion("CHECK_STATUS_NAME not between", value1, value2, "checkStatusName");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNull() {
            addCriterion("CHECK_REASON is null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIsNotNull() {
            addCriterion("CHECK_REASON is not null");
            return (Criteria) this;
        }

        public Criteria andCheckReasonEqualTo(String value) {
            addCriterion("CHECK_REASON =", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotEqualTo(String value) {
            addCriterion("CHECK_REASON <>", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThan(String value) {
            addCriterion("CHECK_REASON >", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonGreaterThanOrEqualTo(String value) {
            addCriterion("CHECK_REASON >=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThan(String value) {
            addCriterion("CHECK_REASON <", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLessThanOrEqualTo(String value) {
            addCriterion("CHECK_REASON <=", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonLike(String value) {
            addCriterion("CHECK_REASON like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotLike(String value) {
            addCriterion("CHECK_REASON not like", value, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonIn(List<String> values) {
            addCriterion("CHECK_REASON in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotIn(List<String> values) {
            addCriterion("CHECK_REASON not in", values, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonBetween(String value1, String value2) {
            addCriterion("CHECK_REASON between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andCheckReasonNotBetween(String value1, String value2) {
            addCriterion("CHECK_REASON not between", value1, value2, "checkReason");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeIsNull() {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeIsNotNull() {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeEqualTo(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME =", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeNotEqualTo(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME <>", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeGreaterThan(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME >", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeGreaterThanOrEqualTo(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME >=", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeLessThan(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME <", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeLessThanOrEqualTo(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME <=", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeLike(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME like", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeNotLike(String value) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME not like", value, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeIn(List<String> values) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME in", values, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeNotIn(List<String> values) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME not in", values, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeBetween(String value1, String value2) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME between", value1, value2, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLastLoginErrorTimeNotBetween(String value1, String value2) {
            addCriterion("APP_LAST_LOGIN_ERROR_TIME not between", value1, value2, "appLastLoginErrorTime");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountIsNull() {
            addCriterion("APP_LOGIN_ERROR_COUNT is null");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountIsNotNull() {
            addCriterion("APP_LOGIN_ERROR_COUNT is not null");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountEqualTo(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT =", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountNotEqualTo(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT <>", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountGreaterThan(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT >", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountGreaterThanOrEqualTo(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT >=", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountLessThan(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT <", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountLessThanOrEqualTo(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT <=", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountLike(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT like", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountNotLike(String value) {
            addCriterion("APP_LOGIN_ERROR_COUNT not like", value, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountIn(List<String> values) {
            addCriterion("APP_LOGIN_ERROR_COUNT in", values, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountNotIn(List<String> values) {
            addCriterion("APP_LOGIN_ERROR_COUNT not in", values, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountBetween(String value1, String value2) {
            addCriterion("APP_LOGIN_ERROR_COUNT between", value1, value2, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAppLoginErrorCountNotBetween(String value1, String value2) {
            addCriterion("APP_LOGIN_ERROR_COUNT not between", value1, value2, "appLoginErrorCount");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNull() {
            addCriterion("AUDIT_STATUS is null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIsNotNull() {
            addCriterion("AUDIT_STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andAuditStatusEqualTo(String value) {
            addCriterion("AUDIT_STATUS =", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotEqualTo(String value) {
            addCriterion("AUDIT_STATUS <>", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThan(String value) {
            addCriterion("AUDIT_STATUS >", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusGreaterThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS >=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThan(String value) {
            addCriterion("AUDIT_STATUS <", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLessThanOrEqualTo(String value) {
            addCriterion("AUDIT_STATUS <=", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusLike(String value) {
            addCriterion("AUDIT_STATUS like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotLike(String value) {
            addCriterion("AUDIT_STATUS not like", value, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusIn(List<String> values) {
            addCriterion("AUDIT_STATUS in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotIn(List<String> values) {
            addCriterion("AUDIT_STATUS not in", values, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andAuditStatusNotBetween(String value1, String value2) {
            addCriterion("AUDIT_STATUS not between", value1, value2, "auditStatus");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxIsNull() {
            addCriterion("ORG_FLOW_GX is null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxIsNotNull() {
            addCriterion("ORG_FLOW_GX is not null");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxEqualTo(String value) {
            addCriterion("ORG_FLOW_GX =", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxNotEqualTo(String value) {
            addCriterion("ORG_FLOW_GX <>", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxGreaterThan(String value) {
            addCriterion("ORG_FLOW_GX >", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxGreaterThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW_GX >=", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxLessThan(String value) {
            addCriterion("ORG_FLOW_GX <", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxLessThanOrEqualTo(String value) {
            addCriterion("ORG_FLOW_GX <=", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxLike(String value) {
            addCriterion("ORG_FLOW_GX like", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxNotLike(String value) {
            addCriterion("ORG_FLOW_GX not like", value, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxIn(List<String> values) {
            addCriterion("ORG_FLOW_GX in", values, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxNotIn(List<String> values) {
            addCriterion("ORG_FLOW_GX not in", values, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxBetween(String value1, String value2) {
            addCriterion("ORG_FLOW_GX between", value1, value2, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andOrgFlowGxNotBetween(String value1, String value2) {
            addCriterion("ORG_FLOW_GX not between", value1, value2, "orgFlowGx");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNull() {
            addCriterion("SCHOOL is null");
            return (Criteria) this;
        }

        public Criteria andSchoolIsNotNull() {
            addCriterion("SCHOOL is not null");
            return (Criteria) this;
        }

        public Criteria andSchoolEqualTo(String value) {
            addCriterion("SCHOOL =", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotEqualTo(String value) {
            addCriterion("SCHOOL <>", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThan(String value) {
            addCriterion("SCHOOL >", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolGreaterThanOrEqualTo(String value) {
            addCriterion("SCHOOL >=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThan(String value) {
            addCriterion("SCHOOL <", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLessThanOrEqualTo(String value) {
            addCriterion("SCHOOL <=", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolLike(String value) {
            addCriterion("SCHOOL like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotLike(String value) {
            addCriterion("SCHOOL not like", value, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolIn(List<String> values) {
            addCriterion("SCHOOL in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotIn(List<String> values) {
            addCriterion("SCHOOL not in", values, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolBetween(String value1, String value2) {
            addCriterion("SCHOOL between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andSchoolNotBetween(String value1, String value2) {
            addCriterion("SCHOOL not between", value1, value2, "school");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdIsNull() {
            addCriterion("USER_LEVEL_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdIsNotNull() {
            addCriterion("USER_LEVEL_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdEqualTo(String value) {
            addCriterion("USER_LEVEL_ID =", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdNotEqualTo(String value) {
            addCriterion("USER_LEVEL_ID <>", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdGreaterThan(String value) {
            addCriterion("USER_LEVEL_ID >", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("USER_LEVEL_ID >=", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdLessThan(String value) {
            addCriterion("USER_LEVEL_ID <", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdLessThanOrEqualTo(String value) {
            addCriterion("USER_LEVEL_ID <=", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdLike(String value) {
            addCriterion("USER_LEVEL_ID like", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdNotLike(String value) {
            addCriterion("USER_LEVEL_ID not like", value, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdIn(List<String> values) {
            addCriterion("USER_LEVEL_ID in", values, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdNotIn(List<String> values) {
            addCriterion("USER_LEVEL_ID not in", values, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdBetween(String value1, String value2) {
            addCriterion("USER_LEVEL_ID between", value1, value2, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelIdNotBetween(String value1, String value2) {
            addCriterion("USER_LEVEL_ID not between", value1, value2, "userLevelId");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameIsNull() {
            addCriterion("USER_LEVEL_NAME is null");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameIsNotNull() {
            addCriterion("USER_LEVEL_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameEqualTo(String value) {
            addCriterion("USER_LEVEL_NAME =", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameNotEqualTo(String value) {
            addCriterion("USER_LEVEL_NAME <>", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameGreaterThan(String value) {
            addCriterion("USER_LEVEL_NAME >", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_LEVEL_NAME >=", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameLessThan(String value) {
            addCriterion("USER_LEVEL_NAME <", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameLessThanOrEqualTo(String value) {
            addCriterion("USER_LEVEL_NAME <=", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameLike(String value) {
            addCriterion("USER_LEVEL_NAME like", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameNotLike(String value) {
            addCriterion("USER_LEVEL_NAME not like", value, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameIn(List<String> values) {
            addCriterion("USER_LEVEL_NAME in", values, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameNotIn(List<String> values) {
            addCriterion("USER_LEVEL_NAME not in", values, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameBetween(String value1, String value2) {
            addCriterion("USER_LEVEL_NAME between", value1, value2, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserLevelNameNotBetween(String value1, String value2) {
            addCriterion("USER_LEVEL_NAME not between", value1, value2, "userLevelName");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlIsNull() {
            addCriterion("USER_SIGN_URL is null");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlIsNotNull() {
            addCriterion("USER_SIGN_URL is not null");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlEqualTo(String value) {
            addCriterion("USER_SIGN_URL =", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlNotEqualTo(String value) {
            addCriterion("USER_SIGN_URL <>", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlGreaterThan(String value) {
            addCriterion("USER_SIGN_URL >", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlGreaterThanOrEqualTo(String value) {
            addCriterion("USER_SIGN_URL >=", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlLessThan(String value) {
            addCriterion("USER_SIGN_URL <", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlLessThanOrEqualTo(String value) {
            addCriterion("USER_SIGN_URL <=", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlLike(String value) {
            addCriterion("USER_SIGN_URL like", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlNotLike(String value) {
            addCriterion("USER_SIGN_URL not like", value, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlIn(List<String> values) {
            addCriterion("USER_SIGN_URL in", values, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlNotIn(List<String> values) {
            addCriterion("USER_SIGN_URL not in", values, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlBetween(String value1, String value2) {
            addCriterion("USER_SIGN_URL between", value1, value2, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andUserSignUrlNotBetween(String value1, String value2) {
            addCriterion("USER_SIGN_URL not between", value1, value2, "userSignUrl");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositIsNull() {
            addCriterion("BANK_OF_DEPOSIT is null");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositIsNotNull() {
            addCriterion("BANK_OF_DEPOSIT is not null");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositEqualTo(String value) {
            addCriterion("BANK_OF_DEPOSIT =", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositNotEqualTo(String value) {
            addCriterion("BANK_OF_DEPOSIT <>", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositGreaterThan(String value) {
            addCriterion("BANK_OF_DEPOSIT >", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_OF_DEPOSIT >=", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositLessThan(String value) {
            addCriterion("BANK_OF_DEPOSIT <", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositLessThanOrEqualTo(String value) {
            addCriterion("BANK_OF_DEPOSIT <=", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositLike(String value) {
            addCriterion("BANK_OF_DEPOSIT like", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositNotLike(String value) {
            addCriterion("BANK_OF_DEPOSIT not like", value, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositIn(List<String> values) {
            addCriterion("BANK_OF_DEPOSIT in", values, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositNotIn(List<String> values) {
            addCriterion("BANK_OF_DEPOSIT not in", values, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositBetween(String value1, String value2) {
            addCriterion("BANK_OF_DEPOSIT between", value1, value2, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankOfDepositNotBetween(String value1, String value2) {
            addCriterion("BANK_OF_DEPOSIT not between", value1, value2, "bankOfDeposit");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIsNull() {
            addCriterion("BANK_ACCOUNT_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIsNotNull() {
            addCriterion("BANK_ACCOUNT_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER =", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER <>", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberGreaterThan(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER >", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberGreaterThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER >=", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLessThan(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER <", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLessThanOrEqualTo(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER <=", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberLike(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER like", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotLike(String value) {
            addCriterion("BANK_ACCOUNT_NUMBER not like", value, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NUMBER in", values, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotIn(List<String> values) {
            addCriterion("BANK_ACCOUNT_NUMBER not in", values, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NUMBER between", value1, value2, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andBankAccountNumberNotBetween(String value1, String value2) {
            addCriterion("BANK_ACCOUNT_NUMBER not between", value1, value2, "bankAccountNumber");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNull() {
            addCriterion("OPEN_ID is null");
            return (Criteria) this;
        }

        public Criteria andOpenIdIsNotNull() {
            addCriterion("OPEN_ID is not null");
            return (Criteria) this;
        }

        public Criteria andOpenIdEqualTo(String value) {
            addCriterion("OPEN_ID =", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotEqualTo(String value) {
            addCriterion("OPEN_ID <>", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThan(String value) {
            addCriterion("OPEN_ID >", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdGreaterThanOrEqualTo(String value) {
            addCriterion("OPEN_ID >=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThan(String value) {
            addCriterion("OPEN_ID <", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLessThanOrEqualTo(String value) {
            addCriterion("OPEN_ID <=", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdLike(String value) {
            addCriterion("OPEN_ID like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotLike(String value) {
            addCriterion("OPEN_ID not like", value, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdIn(List<String> values) {
            addCriterion("OPEN_ID in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotIn(List<String> values) {
            addCriterion("OPEN_ID not in", values, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdBetween(String value1, String value2) {
            addCriterion("OPEN_ID between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andOpenIdNotBetween(String value1, String value2) {
            addCriterion("OPEN_ID not between", value1, value2, "openId");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeIsNull() {
            addCriterion("UN_LOCK_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeIsNotNull() {
            addCriterion("UN_LOCK_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeEqualTo(String value) {
            addCriterion("UN_LOCK_TIME =", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeNotEqualTo(String value) {
            addCriterion("UN_LOCK_TIME <>", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeGreaterThan(String value) {
            addCriterion("UN_LOCK_TIME >", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeGreaterThanOrEqualTo(String value) {
            addCriterion("UN_LOCK_TIME >=", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeLessThan(String value) {
            addCriterion("UN_LOCK_TIME <", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeLessThanOrEqualTo(String value) {
            addCriterion("UN_LOCK_TIME <=", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeLike(String value) {
            addCriterion("UN_LOCK_TIME like", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeNotLike(String value) {
            addCriterion("UN_LOCK_TIME not like", value, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeIn(List<String> values) {
            addCriterion("UN_LOCK_TIME in", values, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeNotIn(List<String> values) {
            addCriterion("UN_LOCK_TIME not in", values, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeBetween(String value1, String value2) {
            addCriterion("UN_LOCK_TIME between", value1, value2, "unLockTime");
            return (Criteria) this;
        }

        public Criteria andUnLockTimeNotBetween(String value1, String value2) {
            addCriterion("UN_LOCK_TIME not between", value1, value2, "unLockTime");
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