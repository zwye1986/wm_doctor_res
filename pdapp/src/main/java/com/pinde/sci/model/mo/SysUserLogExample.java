package com.pinde.sci.model.mo;

import java.util.ArrayList;
import java.util.List;

public class SysUserLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysUserLogExample() {
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

        public Criteria andArchiveFlowIsNull() {
            addCriterion("ARCHIVE_FLOW is null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIsNotNull() {
            addCriterion("ARCHIVE_FLOW is not null");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW =", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <>", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThan(String value) {
            addCriterion("ARCHIVE_FLOW >", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowGreaterThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW >=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThan(String value) {
            addCriterion("ARCHIVE_FLOW <", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLessThanOrEqualTo(String value) {
            addCriterion("ARCHIVE_FLOW <=", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowLike(String value) {
            addCriterion("ARCHIVE_FLOW like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotLike(String value) {
            addCriterion("ARCHIVE_FLOW not like", value, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotIn(List<String> values) {
            addCriterion("ARCHIVE_FLOW not in", values, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW between", value1, value2, "archiveFlow");
            return (Criteria) this;
        }

        public Criteria andArchiveFlowNotBetween(String value1, String value2) {
            addCriterion("ARCHIVE_FLOW not between", value1, value2, "archiveFlow");
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